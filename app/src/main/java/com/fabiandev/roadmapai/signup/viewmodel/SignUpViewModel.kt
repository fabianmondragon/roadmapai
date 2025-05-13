package com.fabiandev.roadmapai.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabiandev.roadmapai.login.domain.AuthenticationUseCase
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.fabiandev.roadmapai.login.utils.Constant.Companion.emailRegex
import com.fabiandev.roadmapai.login.utils.Constant.Companion.passwordRegex
import com.fabiandev.roadmapai.login.utils.NavigationEventUi
import com.fabiandev.roadmapai.login.utils.ResultUi
import com.fabiandev.roadmapai.login.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

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
    private val _navigationEvent = MutableSharedFlow<NavigationEventUi>()  // No initial state
    val navigationEvent: SharedFlow<NavigationEventUi> = _navigationEvent

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent

     fun signUp(){
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.ShowLoading)
            val result = authenticationUseCase.registerUser(email= _email.value, password = _password.value)
            when (result){
                is RoadMapResult.Fail -> {
                    _uiEvent.emit(UiEvent.HideLoading)
                    _uiEvent.emit(UiEvent.ShowToast(result.msg))
                }
                is RoadMapResult.Processing -> {
                    _uiEvent.emit(UiEvent.HideLoading)
                }
                is RoadMapResult.Success -> {
                    _uiEvent.emit(UiEvent.HideLoading)
                    _navigationEvent.emit(NavigationEventUi.NavigateToHello)
                }
            }
        }
    }

    fun onEmailChanged(newEmail: String, ) {
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
        _emailError.value = if (email.matches(emailRegex)) ResultUi.Success else ResultUi.Fail()
    }

    private fun validatePassword(password: String) {
        _passwordError.value = if (password.matches(passwordRegex)) ResultUi.Success else ResultUi.Fail()
    }

    private fun validateRepeatPassword(){
        _repeatPasswordError.value = if (validateSamePassword()) ResultUi.Success else ResultUi.Fail()
    }

    fun thereIsError(): Boolean {
        val result = (_emailError.value == ResultUi.Success && _passwordError.value == ResultUi.Success && _repeatPasswordError.value == ResultUi.Success)
        return result
    }


    fun validateFormAndNavigate() {
        /*viewModelScope.launch {
            _navigationEvent.emit("home") // Use your desired navigation route
        }*/

    }
    private fun validateSamePassword() = (_password.value == _repeatPassword.value)


}