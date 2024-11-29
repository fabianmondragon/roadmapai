package com.fabiandev.roadmapai.login.utils

sealed class ResultUi {
    data object Success : ResultUi()
    data class Fail(val msg: String = "There is an error") : ResultUi()
    data object InitialState: ResultUi()


}