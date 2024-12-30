package com.fabiandev.roadmapai.login.domain

import com.fabiandev.roadmapai.login.domain.models.AuthenticationResponse
import com.fabiandev.roadmapai.login.domain.models.LoginEntity
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult

interface AuthenticationRepository {

    suspend fun registerUser(email:String, password: String): RoadMapResult<AuthenticationResponse>
    suspend fun loginUser(email: String, password: String): RoadMapResult<LoginEntity>
    suspend fun logout()
}