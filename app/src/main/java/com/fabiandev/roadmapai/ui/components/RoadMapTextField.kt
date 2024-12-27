package com.fabiandev.roadmapai.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.fabiandev.roadmapai.ui.theme.Pink40
import com.fabiandev.roadmapai.ui.theme.Purple80
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80



@Composable
fun RoadMapOutlineTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None

) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange.invoke(it) },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Pink40,
            unfocusedLabelColor = Pink40,
            focusedBorderColor = PurpleGrey80,
            cursorColor = Purple80,
            focusedLabelColor = Purple80,
            focusedTextColor = PurpleGrey80,
            unfocusedTextColor = Pink40
        )
    )
}

@Composable
fun RoadMapBigTitle(
    text: String,
    align: Modifier

){
    Text(
        text = text,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = align
    )
}
