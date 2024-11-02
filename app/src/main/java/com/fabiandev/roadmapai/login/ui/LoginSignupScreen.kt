package com.fabiandev.roadmapai.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.R
import com.fabiandev.roadmapai.ui.components.RoadMapNavigationButton
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Pink80


@Composable
@Preview
fun PreviewLoginSignUp() {
    LoginSignUpScreen(navController = rememberNavController())
}

@Composable
fun LoginSignUpScreen(navController: NavHostController) {

    val textLogin = buildAnnotatedString {
        append("Already have an account? ")
        pushStyle(SpanStyle(textDecoration = TextDecoration.Underline))
        append("Sign in here")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Pink40, Pink80)
                )
            )

    ) {

        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.size_40dp))
                .align(Alignment.TopCenter)


        )
        {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.app_name),

                fontSize = 16.sp,
                color = Color.White,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(40.dp)

        )
        {


            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_80dp)))

            // Main Greeting Text
            Text(
                text = stringResource(id = R.string.main_greeting),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White

            )
            Text(
                text = stringResource(id = R.string.sub_greeting),
                fontSize =  24.sp,
                color = Color.White,

                )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_80dp)))

            RoadMapNavigationButton(
                navController = navController,
                route = "signup",
                text = stringResource(id = R.string.signup),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16dp)))

            Text(
                text = stringResource(id = R.string.or),
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_16dp)))

            RoadMapNavigationButton(
                navController = navController,
                route = "signup",
                text = stringResource(id = R.string.signup_google),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.size_50dp))
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_32dp)))

            Text(
                text = textLogin,
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.size_16dp))
            )

        }
    }
}