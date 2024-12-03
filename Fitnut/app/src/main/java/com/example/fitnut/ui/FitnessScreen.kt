package com.example.fitnut.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.material.Text


@Composable
fun FitnessScreen(navController: NavController, modifier: Modifier = Modifier) {
    // Use the modifier here
    Text("Fitness Screen", modifier = modifier)
}
