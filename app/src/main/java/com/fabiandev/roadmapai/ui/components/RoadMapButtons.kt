package com.fabiandev.roadmapai.ui.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fabiandev.roadmapai.ui.theme.Purple40
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80


@Composable
fun RoadMapNavigationButton(
    navController: NavController,
    route: String,
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    isEnabled: Boolean = true
) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40, // Background color of the button
            contentColor = Color.White, // Text color of the button
            disabledContainerColor = PurpleGrey80
        ),
        enabled = isEnabled,


    ) {
        Text(text = text)
    }
}
