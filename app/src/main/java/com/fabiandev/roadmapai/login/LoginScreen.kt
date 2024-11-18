package com.fabiandev.roadmapai.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Pink80
import com.fabiandev.roadmapai.ui.theme.Purple80
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80

@Composable
fun LoginScreen(onLogin: ((String, String) -> Unit)?, navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Box(
        Modifier

            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(PurpleGrey80, Purple80)
                )
            )
    )
    {

        Column(
            modifier = Modifier
                .fillMaxWidth() // Make sure the column takes up the full width of the screen
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {

            Text(
                text = stringResource(id = R.string.main_greeting),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Start),


                )

            Text(
                text = stringResource(id = R.string.main_greeting),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White

            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password TextField
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            RoadMapNavigationButton(
                navController = navController,
                route = "home",
                text = "Sign Up",
            )
        }

    }
}

@Composable
@Preview
fun Preview() {
    LoginScreen(onLogin = null, navController = rememberNavController())
}




