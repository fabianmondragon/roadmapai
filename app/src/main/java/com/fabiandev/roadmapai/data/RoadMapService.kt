package com.fabiandev.roadmapai.data

import com.fabiandev.roadmapai.login.data.FirebaseAuthenticationRepositoryImpl
import com.fabiandev.roadmapai.login.data.response.VerifyTokenResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RoadMapService {

    @POST("verify")
    suspend fun verifyToken(@Body idToken: RequestBody): Response<VerifyTokenResponse>
}