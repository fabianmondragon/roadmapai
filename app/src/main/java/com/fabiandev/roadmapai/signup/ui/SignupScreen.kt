package com.fabiandev.roadmapai.signup.ui

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.login.ui.RoadMapRoute
import com.fabiandev.roadmapai.signup.viewmodel.SignUpViewModel
import com.fabiandev.roadmapai.login.utils.ResultUi
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40

import com.fabiandev.roadmapai.ui.theme.Purple80
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80
import com.fabiandev.roadmapai.ui.utils.RoadMapLoader


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
) {
    val email by signUpViewModel.email.collectAsState()
    val emailError by signUpViewModel.emailError.collectAsState()

    val password by signUpViewModel.password.collectAsState()
    val passwordError by signUpViewModel.passwordError.collectAsState()

    val repeatPassword by signUpViewModel.repeatPassword.collectAsState()
    val repeatPasswordError by signUpViewModel.repeatPasswordError.collectAsState()

    val navigation by signUpViewModel.navigationEvent.collectAsState()
    Log.i("Navigation", "SignUpScreen")

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
                isError = emailError is ResultUi.Fail,
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
            if (emailError is ResultUi.Fail) {
                Text(
                    text = (emailError as ResultUi.Fail).msg,
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
                isError = passwordError is ResultUi.Fail,
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

            if (passwordError is ResultUi.Fail) {
                Text(
                    text = (passwordError as ResultUi.Fail).msg,
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
                isError = repeatPasswordError is ResultUi.Fail,
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

            if (repeatPasswordError is ResultUi.Fail) {
                Text(
                    text = (repeatPasswordError as ResultUi.Fail).msg,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            RoadMapNavigationButton(
                isEnabled = signUpViewModel.thereIsError(),
                //route = RoadMapRoute.hello.toString(),
                text = "Sign Up",
                onClick = {
                    signUpViewModel.signUp()

                }
            )
        }
    }
    OnNavigationEvent(navigation, navController)
}

@Composable
fun OnNavigationEvent(navigation: ResultUi, navController: NavHostController) {

    when (navigation) {
        is ResultUi.Fail -> RoadMapLoader(isLoading = false)
        ResultUi.InitialState -> RoadMapLoader(isLoading = false)
        ResultUi.Proccesing -> RoadMapLoader(isLoading = true)
        ResultUi.Success -> {

            RoadMapLoader(isLoading = false)
            LaunchedEffect(navigation) {
                navController.navigate(RoadMapRoute.Hello.toString())
            }

        }

    }
}


@Composable
@Preview
fun PreviewSignUp() {
    SignUpScreen(navController = rememberNavController())
}