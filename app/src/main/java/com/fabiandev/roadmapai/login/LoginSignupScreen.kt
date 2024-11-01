package com.fabiandev.roadmapai.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
                .padding(40.dp)
                .align(Alignment.TopCenter)


        )
        {
            Text(
                modifier = Modifier.fillMaxWidth().padding(40.dp),
                text = "A.M",

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


            Spacer(modifier = Modifier.height(80.dp))

            // Main Greeting Text
            Text(
                text = "Hello.",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White

            )
            Text(
                text = "Let’s Get Started!",
                fontSize = 24.sp,
                color = Color.White,

                )

            Spacer(modifier = Modifier.height(80.dp))

            RoadMapNavigationButton(
                navController = navController,
                route = "signup",
                text = "Go to Signup",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "or",
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            RoadMapNavigationButton(
                navController = navController,
                route = "signup",
                text = "Go to Google",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Already have an account? Sign in here.",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )

        }
    }
}