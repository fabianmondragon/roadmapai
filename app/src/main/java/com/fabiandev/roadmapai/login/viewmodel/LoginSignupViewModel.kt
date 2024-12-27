package com.fabiandev.roadmapai.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiandev.roadmapai.login.domain.AuthenticationUseCase
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.fabiandev.roadmapai.login.utils.ResultUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginSignupViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
): ViewModel() {

    private val _formState = MutableStateFlow(UiStateLogin())
    val formState: StateFlow <UiStateLogin> = _formState.asStateFlow()

    private val _navigationEvent = MutableStateFlow<ResultUi>(ResultUi.InitialState)
    val navigationEvent: StateFlow<ResultUi> = _navigationEvent.asStateFlow()

    fun login() {
        viewModelScope.launch (Dispatchers.IO) {
            _navigationEvent.value = ResultUi.Proccesing
            val result = authenticationUseCase.loginUser(formState.value.email, formState.value.password)
            when (result){
                is RoadMapResult.Fail -> {
                    _navigationEvent.value = ResultUi.Fail (result.msg)
                }
                is RoadMapResult.Processing -> {
                    _navigationEvent.value = ResultUi.Proccesing
                }
                is RoadMapResult.Success -> {
                    Log.i("Navigation", "Success")
                    _navigationEvent.value = ResultUi.Success
                }
            }
        }
    }

}