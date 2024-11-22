package com.fabiandev.roadmapai.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.login.RoadMapRoute
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Pink80

@Composable
@Preview
fun PreviewLoginSignUp() {
    HomeScreen(navController = rememberNavController())
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val textLogin = buildAnnotatedString {
        append("Already have an account? ")
        pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
        append("Sign in here")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(Pink40, Pink80)))
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.size_40dp))
                .align(Alignment.TopCenter)
        ) {
            // App Name Header
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 40.dp)
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_80dp)))

            // Main Greeting Text
            MainGreetingText()

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_80dp)))

            // Sign Up Button
            RoadMapNavigationButton(
                navController = navController,
                route = RoadMapRoute.signup.toString(),
                text = stringResource(id = R.string.signup),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16dp)))

            // "OR" Text
            Text(
                text = stringResource(id = R.string.or),
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16dp)))

            // Google Sign Up Button
            RoadMapNavigationButton(
                navController = navController,
                route = RoadMapRoute.signup.toString(),
                text = stringResource(id = R.string.signup_google),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.size_50dp))
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_32dp)))

            // Login Text with Clickable Navigation
            LoginRedirectText(textLogin = textLogin, navController = navController)
        }
    }
}

@Composable
fun MainGreetingText() {
    Text(
        text = stringResource(id = R.string.main_greeting),
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
    Text(
        text = stringResource(id = R.string.sub_greeting),
        fontSize = 24.sp,
        color = Color.White
    )
}

@Composable
fun LoginRedirectText(textLogin: AnnotatedString, navController: NavHostController) {
    Text(
        text = textLogin,
        fontSize = 14.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable {
                navController.navigate(RoadMapRoute.login.toString())
            }
            .padding(top = dimensionResource(id = R.dimen.size_16dp))
    )
}
