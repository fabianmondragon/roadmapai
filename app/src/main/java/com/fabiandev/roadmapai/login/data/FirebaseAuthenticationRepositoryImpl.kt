package com.fabiandev.roadmapai.login.data

import android.util.Log
import com.fabiandev.roadmapai.login.domain.AuthenticationRepository
import com.fabiandev.roadmapai.login.domain.models.AuthenticationResponse
import com.fabiandev.roadmapai.login.domain.models.LoginEntity
import com.fabiandev.roadmapai.login.domain.util.RoadMapResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import com.google.firebase.firestore.FieldValue



class FirebaseAuthenticationRepositoryImpl @Inject constructor(private val mAuth: FirebaseAuth) :
    AuthenticationRepository {
    override suspend fun registerUser(
        userName: String,
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
                            "name" to userName,
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
        return suspendCancellableCoroutine {continuation ->
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        getToken()
                        continuation.resume(
                            RoadMapResult.Success (
                                data = LoginEntity(userName = mAuth.currentUser!!.email!!, identifierUser = mAuth.currentUser!!.uid),
                                message = "success"
                            )
                        )
                    }
                    else {
                        val errorMessage = task.exception?.message ?: "Unknown error"
                        Log.e("FirebaseAuth", "Login Faild: $errorMessage")
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

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

    private fun getToken(){
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.getIdToken(true)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idToken = task.result?.token
                Log.d("IDToken", "Token: $idToken")

                // Send the token to your server
                //sendTokenToServer(idToken)
            } else {
                Log.e("TokenError", "Failed to get token: ${task.exception}")
            }
        } ?: Log.e("TokenError", "User is not signed in.")
    }
}