package com.fabiandev.roadmapai.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fabiandev.roadmapai.login.utils.ResultUi
import com.fabiandev.roadmapai.ui.theme.Error80

@Composable
fun RoadMapErrorInOutlineTextField (
    text: String
) {
    Text(
        text = text,
        color = Error80,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(top = 4.dp)
    )
}