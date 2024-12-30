package com.fabiandev.roadmapai.login.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.login.utils.ResultUi
import com.fabiandev.roadmapai.login.viewmodel.LoginViewModel
import com.fabiandev.roadmapai.ui.components.RoadMapBigTitle
import com.fabiandev.roadmapai.ui.components.RoadMapErrorInOutlineTextField
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.components.RoadMapOutlineTextField
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Pink80
import com.fabiandev.roadmapai.ui.utils.CustomToastExample
import com.fabiandev.roadmapai.ui.utils.RoadMapLoader

@Composable
fun LoginScreen(
    onLogin: ((String, String) -> Unit)?,
    navController: NavHostController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val formState by loginViewModel.formState.collectAsState()
    val navigationEvent by loginViewModel.navigationEvent.collectAsState()

    Log.i("Navigation", "LoginScreen")

    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Pink40, Pink80)
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
            RoadMapBigTitle(
                text = stringResource(id = R.string.login),
                Modifier.align(Alignment.Start)
            )

            RoadMapOutlineTextField(
                value = formState.email,
                label = "Email",
                onValueChange = { loginViewModel.onEmailChange(it) }
            )
            if (formState.emailFormatError is ResultUi.Fail) {
                RoadMapErrorInOutlineTextField(text = (formState.emailFormatError as ResultUi.Fail).msg)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Password TextField
            RoadMapOutlineTextField(
                value = formState.password,
                label = "Password",
                onValueChange = { loginViewModel.onPasswordChange(it) },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            RoadMapNavigationButton(
                isEnabled = loginViewModel.allOk(),
                text = "Login",
                onClick = {
                    loginViewModel.login()
                }
            )
        }
    }
    OnNavigationEvent(navigationEvent, navController)
}

@Composable
fun OnNavigationEvent(navigation: ResultUi, navController: NavHostController) {
    when (navigation) {
        is ResultUi.Fail -> {
            Log.i("Navigation", "Enter fail")
            //LoaderExample(isLoading = false)
            CustomToastExample(message = navigation.msg)
        }

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
fun Preview() {
    LoginScreen(onLogin = null, navController = rememberNavController())
}




