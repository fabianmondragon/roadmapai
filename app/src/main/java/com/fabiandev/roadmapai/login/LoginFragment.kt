package com.fabiandev.roadmapai.login

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.login.ui.LoginSignUpScreen

/**
 * is function to receive the information of Login and register
 * @param nothing
 */
@Composable
fun MainLoginRegisterScreen() {
    // is a helper function that provides a NavController
    // the navController is the core component that enables Composes screen(composables) to navigate between each other.
    // retine the NavController instace during recompositions, preventing unexpected resets of the navigation stack
    val navController = rememberNavController()

    // The navHost composable is used to define the navigation graph.
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