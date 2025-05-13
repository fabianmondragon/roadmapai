package com.fabiandev.roadmapai.login.data

import android.util.Log
import com.fabiandev.roadmapai.data.RoadMapService
import com.fabiandev.roadmapai.login.domain.AuthenticationRepository
import com.fabiandev.roadmapai.login.domain.models.AuthenticationResponse
import com.fabiandev.roadmapai.login.domain.models.LoginEntity
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody


class FirebaseAuthenticationRepositoryImpl @Inject constructor(
    private val mAuth: FirebaseAuth,
    private val roadMapService: RoadMapService
) :
    AuthenticationRepository {
    override suspend fun registerUser(
        email: String,
        password: String,
    ): RoadMapResult<AuthenticationResponse> {
        return suspendCancellableCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        val userId = user?.uid
                        val userData = hashMapOf(
                            "userId" to userId,
                            "email" to email,
                            "registeredAt" to FieldValue.serverTimestamp()
                        )


                        Log.d("FirebaseAuth", "User registered successfully")
                        continuation.resume(
                            RoadMapResult.Success(
                                data = AuthenticationResponse("user"),
                                message = "success"
                            )
                        )
                    } else {
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        Log.e("FirebaseAuth", "Registration failed: $errorMessage")
                        continuation.resume(
                            RoadMapResult.Fail(errorMessage)
                        )
                    }
                }
                .addOnCanceledListener {
                    continuation.resumeWithException(CancellationException("Firebase registration canceled"))
                }
        }
    }

    override suspend fun loginUser(email: String, password: String): RoadMapResult<LoginEntity> {
        return try {
            val user = signInWithEmailAndPassword(email, password)
            val token = getToken(user)
            if (verifyToken(token)) {
                RoadMapResult.Success(
                    data = LoginEntity(
                        userName = user.email ?: "Unknown",
                        identifierUser = user.uid
                    ),
                    message = "Login successful. Token: $token"
                )
            }else {
                RoadMapResult.Fail("Unknown error")

            }
        } catch (e: Exception) {
            Log.e("FirebaseAuth", "Login Failed: ${e.message}")
            RoadMapResult.Fail(e.message ?: "Unknown error")
        }
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    private suspend fun signInWithEmailAndPassword(email: String, password: String): FirebaseUser {
        return suspendCancellableCoroutine { continuation ->
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Firebase", "Login successfully")
                        val user = task.result?.user
                        if (user != null) {
                            continuation.resume(user)
                        } else {
                            continuation.resumeWithException(IllegalStateException("User is null after successful login"))
                        }
                    } else {
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        continuation.resumeWithException(Exception(errorMessage))
                    }
                }
                .addOnCanceledListener {
                    continuation.resumeWithException(CancellationException("Firebase login canceled"))
                }
        }
    }

    private suspend fun getToken(user: FirebaseUser): String {
        return suspendCancellableCoroutine { continuation ->
            user.getIdToken(true)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Firebase", "token successfully")
                        val token = task.result?.token
                        if (token != null) {
                            continuation.resume(token)
                        } else {
                            continuation.resumeWithException(IllegalStateException("Token is null"))
                        }
                    } else {
                        val errorMessage = task.exception?.message ?: "Failed to fetch token"
                        continuation.resumeWithException(Exception(errorMessage))
                    }
                }
        }
    }

    private suspend fun verifyToken(token: String?): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (token == null) {
                    return@withContext false
                }
                val requestBody = token.toRequestBody("text/plain".toMediaType())
                val response = roadMapService.verifyToken(requestBody)

                if (response.isSuccessful) {
                    Log.i("Firebase", "verify token successfully")
                    true
                } else {
                    false
                }
            } catch (e: Exception) {
                Log.i("Firebase", e.localizedMessage)
                false
            }
        }
    }
    data class TokenRequest(val idToken: String)


}