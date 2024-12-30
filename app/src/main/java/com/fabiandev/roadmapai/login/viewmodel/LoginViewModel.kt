package com.fabiandev.roadmapai.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiandev.roadmapai.login.domain.AuthenticationUseCase
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.fabiandev.roadmapai.login.utils.Constant
import com.fabiandev.roadmapai.login.utils.Constant.Companion.emailRegex
import com.fabiandev.roadmapai.login.utils.ResultUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {
    private val _formState = MutableStateFlow(UiStateLogin())
    val formState: StateFlow<UiStateLogin> = _formState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<ResultUi>(ResultUi.InitialState)
    val navigationEvent: StateFlow<ResultUi> = _navigationEvent

    fun login() {
        Log.i("Navigation", "Login")
        _navigationEvent.value = ResultUi.Proccesing
        viewModelScope.launch {
            val result = authenticationUseCase.loginUser(
                email = _formState.value.email,
                password = _formState.value.password
            )
            when (result) {
                is RoadMapResult.Fail -> {
                    Log.i("Navigation", "fail")
                    _navigationEvent.value = ResultUi.Fail(result.msg)
                }

                is RoadMapResult.Processing -> {
                    Log.i("Navigation", "Proccesing")
                    _navigationEvent.value = ResultUi.Proccesing
                }

                is RoadMapResult.Success -> {
                    Log.i("Navigation", "Success")
                    _navigationEvent.value = ResultUi.Success
                }
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _formState.update {state ->
            state.copy(
                email = newEmail,
                emailFormatError = validateEmail(newEmail)
            )
        }
    }
    fun onPasswordChange(newPassword: String) {
        _formState.update {state ->
            state.copy(
                password = newPassword
            )
        }
    }

    fun allOk(): Boolean {
        return  (_formState.value.emailFormatError == ResultUi.Success && _formState.value.password != "")
    }

    private fun validateEmail(email: String): ResultUi {
        return if (email.matches(emailRegex)) ResultUi.Success else ResultUi.Fail("Invalid email format")
    }

}