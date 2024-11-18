package com.fabiandev.roadmapai.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Pink80
import com.fabiandev.roadmapai.ui.theme.Purple40
import com.fabiandev.roadmapai.ui.theme.Purple80
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80
import com.fabiandev.roadmapai.ui.theme.transparent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                text = stringResource(id = R.string.signup),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Pink40,
                modifier = Modifier.align(Alignment.Start),


                )

            Text(
                text = stringResource(id = R.string.up),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Pink40

            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40
                )
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40


                ),

                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password TextField
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40

                ),
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
fun PreviewSignUp() {
    SignUpScreen(navController = rememberNavController())
}