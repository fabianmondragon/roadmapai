package com.fabiandev.roadmapai.signup.ui

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
import com.fabiandev.roadmapai.hello.HelloScreen
import com.fabiandev.roadmapai.login.ui.RoadMapRoute
import com.fabiandev.roadmapai.login.utils.NavigationEventUi
import com.fabiandev.roadmapai.signup.viewmodel.SignUpViewModel
import com.fabiandev.roadmapai.login.utils.ResultUi
import com.fabiandev.roadmapai.login.utils.UiEvent
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
    val email by signUpViewModel.email.collectAsState()
    val emailError by signUpViewModel.emailError.collectAsState()

    val password by signUpViewModel.password.collectAsState()
    val passwordError by signUpViewModel.passwordError.collectAsState()

    val repeatPassword by signUpViewModel.repeatPassword.collectAsState()
    val repeatPasswordError by signUpViewModel.repeatPasswordError.collectAsState()


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
                label = { Text(stringResource(id = R.string.email)) },
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
                    text = stringResource(R.string.invalid_email_format),
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
                label = { Text(stringResource(id = R.string.password)) },
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
                    text = stringResource(R.string.invalid_password_format),
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
                label = { Text(stringResource(id = R.string.confirm_password)) },
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
                    text = stringResource(R.string.passwords_do_not_match),
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
    LaunchedEffect(Unit) {
        signUpViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    // Handle the ShowToast event
                }

                is UiEvent.HideErrorScreen -> {
                    // Handle the HideErrorScreen event
                }

                is UiEvent.HideLoading -> {
                    // Handle the HideLoading event
                }

                is UiEvent.ShowErrorScreen -> {
                    // Handle the ShowErrorScreen event
                }

                is UiEvent.ShowLoading -> {
                    // Handle the ShowLoading event
                }

                is UiEvent.ShowSnackbar -> {
                    // Handle the ShowSnackbar event
                }
            }

        }
    }
    LaunchedEffect(Unit) {
        signUpViewModel.navigationEvent.collect { event ->
            when (event) {
                is NavigationEventUi.NavigateToHello -> {
                    navController.navigate(RoadMapRoute.Hello.toString()+  "?signupSuccess=${true}") //?signupSuccess=true")
                }

            }
        }
    }
}


@Composable
fun OnUiEvent(uiEvent: UiEvent, navController: NavHostController) {
    when (uiEvent) {
        is UiEvent.ShowToast -> {
            // Handle the ShowToast event
        }

        is UiEvent.HideErrorScreen -> {

        }

        is UiEvent.HideLoading -> {

        }

        is UiEvent.ShowErrorScreen -> {

        }

        is UiEvent.ShowLoading -> {

        }

        is UiEvent.ShowSnackbar -> {

        }
    }
}

@Composable
@Preview
fun PreviewSignUp() {
    SignUpScreen(navController = rememberNavController())
}

@Composable
@Preview
fun PreviewHelloScreen() {
    HelloScreen(navController = rememberNavController(), signupSuccess = true)
}