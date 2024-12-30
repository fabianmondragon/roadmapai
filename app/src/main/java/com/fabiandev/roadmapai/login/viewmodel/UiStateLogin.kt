package com.fabiandev.roadmapai.login.viewmodel

import com.fabiandev.roadmapai.login.utils.ResultUi

data class UiStateLogin (
    val email: String = "",
    val password: String = "",
    val emailFormatError: ResultUi = ResultUi.InitialState
)





