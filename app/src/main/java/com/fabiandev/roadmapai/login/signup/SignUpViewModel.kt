package com.fabiandev.roadmapai.login.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiandev.roadmapai.login.utils.Constant.Companion.emailRegex
import com.fabiandev.roadmapai.login.utils.Constant.Companion.passwordRegex
import com.fabiandev.roadmapai.login.utils.ResultUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    // MutableStateFlow for live UI state updates
    private val _email = MutableStateFlow("")
    val email: StateFlow<String?> = _email

    // Email error state
    private val _emailError = MutableStateFlow<ResultUi>(ResultUi.InitialState)
    val emailError: StateFlow<ResultUi> = _emailError

    private val _password = MutableStateFlow("")
    val password: StateFlow<String?> = _password

    private val _passwordError = MutableStateFlow<ResultUi>(ResultUi.InitialState)
    val passwordError: StateFlow<ResultUi> = _passwordError

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String?> = _repeatPassword

    private val _repeatPasswordError = MutableStateFlow<ResultUi>(ResultUi.InitialState)
    val repeatPasswordError: StateFlow<ResultUi> = _repeatPasswordError

    // SharedFlow for navigation events
    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent: SharedFlow<String> = _navigationEvent.asSharedFlow()

    fun onEmailChanged(newEmail: String) {
        _email.update { newEmail }
        validateEmail(newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        _password.update { newPassword }
        validatePassword(newPassword)
    }

    fun onRepeatPasswordChange(repeatNewPassword: String) {
        _repeatPassword.update { repeatNewPassword }
        validateRepeatPassword()
    }

    private fun validateEmail(email: String) {
        _emailError.value = if (email.matches(emailRegex)) ResultUi.Success else ResultUi.Fail("Invalid email format")
    }

    private fun validatePassword(password: String) {
        _passwordError.value = if (password.matches(passwordRegex)) ResultUi.Success else ResultUi.Fail("Invalid password format")
    }

    private fun validateRepeatPassword(){
        _repeatPasswordError.value = if ( validateSamePassword()) ResultUi.Success else ResultUi.Fail("The password do not match")
    }

    fun thereIsError(): Boolean {
        val result = (_emailError.value == ResultUi.Success && _passwordError.value == ResultUi.Success && _repeatPasswordError.value == ResultUi.Success)
        return result
    }


    fun validateFormAndNavigate() {
        viewModelScope.launch {
            _navigationEvent.emit("home") // Use your desired navigation route
        }

    }
    private fun validateSamePassword() = (_password.value == _repeatPassword.value)


}