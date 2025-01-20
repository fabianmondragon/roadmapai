package com.fabiandev.roadmapai.login.data.response

data class VerifyTokenResponse(
    val success: Boolean,
    val message: String,
    val uid: String?,
    val email: String?
)
