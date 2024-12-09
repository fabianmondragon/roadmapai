package com.fabiandev.roadmapai.login.domain

import com.fabiandev.roadmapai.login.domain.models.AuthenticationResponse
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun registerUser(email:String, password: String): RoadMapResult<AuthenticationResponse> {
        return authenticationRepository.registerUser(email, password)
    }
    suspend fun loginUser(email: String, password: String) {
        authenticationRepository.loginUser(email, password)
    }

    suspend fun logout(){

    }


}