package com.fabiandev.roadmapai.login.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.hello.HelloScreen
import com.fabiandev.roadmapai.signup.ui.SignUpScreen

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
        startDestination = RoadMapRoute.hello.toString()
    ) {
        composable(RoadMapRoute.hello.toString()) { HelloScreen(navController) }
        composable(RoadMapRoute.login.toString()) { LoginScreen(::onLogin, navController) }
        composable(RoadMapRoute.signup.toString()) { SignUpScreen(navController) }
    }
}
sealed class RoadMapRoute {
    object hello: RoadMapRoute()
    object login: RoadMapRoute()
    object signup: RoadMapRoute()
}

fun onLogin(username: String, password: String): Unit {


}