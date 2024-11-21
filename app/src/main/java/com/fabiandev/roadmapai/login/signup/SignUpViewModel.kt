package com.fabiandev.roadmapai.login.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiandev.roadmapai.login.utils.Constant.Companion.emailRegex
import com.fabiandev.roadmapai.login.utils.Constant.Companion.passwordRegex
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
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError

    private val _password = MutableStateFlow("")
    val password: StateFlow<String?> = _password

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError

    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword: StateFlow<String?> = _repeatPassword

    private val _repeatPasswordError = MutableStateFlow<String?>(null)
    val repeatPasswordError: StateFlow<String?> = _repeatPasswordError

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
        _emailError.value = if (email.matches(emailRegex)) null else "Invalid email format"
    }

    private fun validatePassword(password: String) {
        _passwordError.value = if (password.matches(passwordRegex)) null else "Invalid password format"
    }

    private fun validateRepeatPassword(){
        _repeatPasswordError.value = if ( validateSamePassword()) null else "The password do not match"
    }

    fun thereIsError() =
        (_emailError.value == null && _passwordError.value == null && _repeatPasswordError.value == null)

    fun validateFormAndNavigate() {
        viewModelScope.launch {
            _navigationEvent.emit("home") // Use your desired navigation route
        }

    }
    private fun validateSamePassword() = (_password.value == _repeatPassword.value)


}