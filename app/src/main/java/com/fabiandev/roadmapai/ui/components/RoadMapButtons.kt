package com.fabiandev.roadmapai.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun RoadMapNavigationButton(
    navController: NavController,
    route: String,
    text: String,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Button(
        onClick = { navController.navigate(route) },
        modifier = modifier,
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(text = text)
    }
}
