package com.fabiandev.roadmapai.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainLoginRegisterScreen() {
    // Create a NavController
    val navController = rememberNavController()
    // Set up the NavHost with the NavController and start destination
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Define the composable screens with routes
        composable("home") { LoginSignUpScreen(navController) }
        composable("login") { LoginScreen(::onLogin, navController) }
        composable("signup") { SignUpScreen(navController) }
    }
}

fun onLogin(username: String, password: String): Unit {

}