package com.fabiandev.roadmapai.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80
import androidx.compose.material3.Text
import com.fabiandev.roadmapai.ui.theme.Pink40


@Composable
fun RoadMapLoader(isLoading: Boolean) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isLoading) Color.Black.copy(alpha = 0.5f) else Color.Transparent)
        )
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(60.dp),
                color = PurpleGrey80,
                strokeWidth = 4.dp
            )
        }
    }
}

@Composable
fun CustomToastExample( message: String) {
    val isToastVisible = remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        isToastVisible.value = false
    }


    Box(Modifier.fillMaxSize()) {

        if (isToastVisible.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
                    .background(PurpleGrey80, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = message,
                    color = Pink40
                )
            }
        }
    }
}
