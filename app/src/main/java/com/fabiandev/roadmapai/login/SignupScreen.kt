package com.fabiandev.roadmapai.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController: NavHostController) {

    Column {
        Text(text = "SignUpScreen")
        Row {

            Button(onClick = { navController.navigate("login") } ) {
                Text(text = "Go to Login")
            }
            Button(onClick = { navController.navigate("home") } ) {
                Text(text = "Go to home")
            }
        }
    }
    
}