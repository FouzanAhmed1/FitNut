package com.example.fitnut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnut.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitNutAppUI()
        }
    }
}

@Composable
fun FitNutAppUI() {
    val navController = rememberNavController() // NavHostController is returned
    Scaffold(
        topBar = { TopAppBar(title = { Text("FitNut") }) },
        content = { innerPadding ->
            Navigation(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    )
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(navController = navController, modifier = modifier) }
        composable("nutrition") { NutritionScreen(navController = navController, modifier = modifier) }
        composable("fitness") { FitnessScreen(navController = navController, modifier = modifier) }
        composable("progress") { ProgressScreen(navController = navController, modifier = modifier) }
    }
}
