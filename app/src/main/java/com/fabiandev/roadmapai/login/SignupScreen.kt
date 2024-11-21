package com.fabiandev.roadmapai.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.login.signup.SignUpViewModel
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40

import com.fabiandev.roadmapai.ui.theme.Purple80
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val email by signUpViewModel.email.collectAsState()
    val emailError by signUpViewModel.emailError.collectAsState()

    val password by signUpViewModel.password.collectAsState()
    val passwordError by signUpViewModel.passwordError.collectAsState()

    val repeatPassword by signUpViewModel.repeatPassword.collectAsState()
    val repeatPasswordError by signUpViewModel.repeatPasswordError.collectAsState()


    // Observe navigation events
    LaunchedEffect(signUpViewModel.navigationEvent) {
        signUpViewModel.navigationEvent.collect { route ->
            navController.navigate(route) // Perform navigation based on the emitted route
        }
    }

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
                value = email ?: "",
                onValueChange = { signUpViewModel.onEmailChanged(it) },
                label = { Text("Email") },
                isError = emailError != null,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40,
                    errorBorderColor = Color.Red,
                    errorTextColor = Pink40,
                    errorLabelColor = Pink40
                )
            )

            if (emailError != null) {
                Text(
                    text = emailError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            OutlinedTextField(
                value = password ?: "",
                onValueChange = { signUpViewModel.onPasswordChange(it) },
                label = { Text("Password") },
                isError = passwordError != null,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40,
                    errorBorderColor = Color.Red,
                    errorTextColor = Pink40,
                    errorLabelColor = Pink40


                ),

                visualTransformation = PasswordVisualTransformation()
            )

            if (passwordError != null) {
                Text(
                    text = passwordError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password TextField
            OutlinedTextField(
                value = repeatPassword ?: "",
                onValueChange = { signUpViewModel.onRepeatPasswordChange(it) },
                label = { Text("Confirm Password") },
                isError = repeatPasswordError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Pink40,
                    unfocusedLabelColor = Pink40,
                    focusedBorderColor = Pink40,
                    cursorColor = Pink40,
                    focusedLabelColor = Pink40,
                    focusedTextColor = Pink40,
                    unfocusedTextColor = Pink40,
                    errorBorderColor = Color.Red,
                    errorTextColor = Pink40,
                    errorLabelColor = Pink40

                ),
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )

            if (repeatPasswordError != null) {
                Text(
                    text = repeatPasswordError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            RoadMapNavigationButton(
                navController = navController,
                isEnabled = signUpViewModel.thereIsError(),
                route = "home",
                text = "Sign Up"
            )
        }
    }
}

@Composable
@Preview
fun PreviewSignUp() {
    //SignUpScreen(navController = rememberNavController())
}