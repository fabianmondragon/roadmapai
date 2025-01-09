package com.fabiandev.roadmapai.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.fabiandev.roadmapai.ui.theme.Purple40
import com.fabiandev.roadmapai.ui.theme.PurpleGrey40
import com.fabiandev.roadmapai.ui.theme.PurpleGrey80

@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        item {
            LevelSelector()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            CardCarousel()
        }

        item {
            ProgressSection()
        }
        item {
            ExamSection()
        }


    }
}

@Composable
fun LevelSelector() {
    val levels = listOf("Beginner", "Intermediate", "Advanced")
    var selectedLevel by remember { mutableStateOf<String?>(null) }

    // Title
    Text(
        text = "Choose Your Level:",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(bottom = 16.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        levels.forEach {
            Box(modifier = Modifier
                .clickable { selectedLevel = it }
                .background(
                    color = if (selectedLevel == it) Color(0xFF4CAF50) else Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    text = it,
                    fontSize = 16.sp,
                    color = if (selectedLevel == it) Color.White else Color.Black
                )
            }
        }
    }
    selectedLevel?.let {
        Text(
            text = "Selected Level: $it",
            modifier = Modifier.padding(top = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CardCarousel() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) { index ->
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp), shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Card $index")
                }
            }
        }
    }
}

@Composable
fun ProgressSection() {
    Text(
        text = "Progress Section",
        modifier = Modifier.padding(PaddingValues(start = 16.dp, top = 16.dp))
    )

    val listOFCourses = listOf("Curso 1", "Curso 2", "Curso 3")
    listOFCourses.forEach { course ->
        Card(
            shape = RectangleShape, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = course, modifier = Modifier.padding(bottom = 8.dp))
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = 0.9f, // Replace with actual progress
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }


}


@Composable
fun ExamSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RectangleShape, // No rounded corners
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Take the Exam")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Take this short exam to determine which roadmap suits you best based on your skills and goals.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Button to start the exam
            Button(
                onClick = {
                    // Navigate to the exam screen or start the exam process
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Exam")
            }
        }
    }
}


@Composable
@Preview
fun Preview() {
    HomeScreen(navController = rememberNavController())
}