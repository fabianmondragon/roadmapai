package com.fabiandev.roadmapai.login.data

import android.util.Log
import androidx.compose.material3.contentColorFor
import com.fabiandev.roadmapai.login.domain.AuthenticationRepository
import com.fabiandev.roadmapai.login.domain.models.AuthenticationResponse
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class FirebaseAuthenticationRepositoryImpl @Inject constructor(private val mAuth: FirebaseAuth) :
    AuthenticationRepository {
    override suspend fun registerUser(
        email: String,
        password: String
    ): RoadMapResult<AuthenticationResponse> {
        return suspendCancellableCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
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


    override suspend fun loginUser(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }
}