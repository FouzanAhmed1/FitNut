package com.example.fitnut.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("nutrition") }) {
            Text("Nutrition Guidance")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("fitness") }) {
            Text("Fitness Tracking")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("progress") }) {
            Text("Daily Progress")
        }
    }
}
