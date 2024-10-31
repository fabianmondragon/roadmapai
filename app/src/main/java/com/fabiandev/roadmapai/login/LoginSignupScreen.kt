package com.fabiandev.roadmapai.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginSignUpScreen(navController: NavHostController) {

    Column {
        Text(text = "LoginAndSignUp Screen")

        Row {
            Button(onClick = { navController.navigate("login") }, modifier =  Modifier.width(100.dp) ) {
                Text(text = "Go to Login")
            }
            Button(onClick = { navController.navigate("signup") }, modifier = Modifier.width(100.dp) ) {
                Text(text = "Go to SignUp")
            }
        }
    }




}