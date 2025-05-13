package com.fabiandev.roadmapai.login.utils

sealed class ResultUi {
    data object Success : ResultUi()
    data class Fail(val msg: String = "There is an error") : ResultUi()
    data object InitialState: ResultUi()
    data object Proccesing: ResultUi()

}

sealed class NavigationEventUi {
    data object NavigateToHello : NavigationEventUi()
}

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
    data class ShowSnackbar(val message: String) : UiEvent()
    data class ShowErrorScreen(val reason: String) : UiEvent()
    data object HideErrorScreen : UiEvent()
    data object ShowLoading : UiEvent()
    data object HideLoading : UiEvent()
}