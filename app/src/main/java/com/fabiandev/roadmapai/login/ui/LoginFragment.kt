package com.fabiandev.roadmapai.login.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fabiandev.roadmapai.hello.HelloScreen
import com.fabiandev.roadmapai.home.ui.HomeScreen
import com.fabiandev.roadmapai.signup.ui.SignUpScreen

/**
 * is function to receive the information of Login and register
 * @param nothing
 */
@Composable
fun MainLoginRegisterScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RoadMapRoute.Hello.toString()
    ) {
        composable(
            route = RoadMapRoute.Hello.toString() + "?signupSuccess={signupSuccess}",
            arguments = listOf(navArgument("signupSuccess") {
                type = NavType.BoolType
                defaultValue = false
                nullable = false
            })
        ) { backStackEntry ->
            val signupSuccess = backStackEntry.arguments?.getBoolean("signupSuccess") ?: false
            print("signupSuccess: $signupSuccess")
            HelloScreen(navController, signupSuccess)
        }
        composable(RoadMapRoute.Login.toString()) {
            LoginScreen(
                ::onLogin,
                navController
            )
        }
        composable(RoadMapRoute.Signup.toString()) { SignUpScreen(navController) }
        composable(RoadMapRoute.Home.toString()) { HomeScreen(navController) }
    }
}

sealed class RoadMapRoute {
    data object Hello : RoadMapRoute()
    data object Login : RoadMapRoute()
    data object Signup : RoadMapRoute()
    data object Home : RoadMapRoute()
}

fun onLogin(username: String, password: String): Unit {


}