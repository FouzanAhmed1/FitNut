package com.example.fitnut.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fitnut.R
import com.example.fitnut.models.Meal
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavController
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color



@Composable
fun MealScreen(
    navController: NavController,
    meals: List<Meal>,
    isLoading: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                LottieAnimationView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(16.dp),
                    animationRes = R.raw.loading_animation
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(meals) { meal ->
                    MealItem(meal = meal)
                }
            }
        }

        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color.White)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Home Icon Button
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    imageVector = Icons.Filled.Home, // Material Icon for Home
                    contentDescription = "Home",
                    tint = Color.Black
                )
            }
            // Help Icon Button
            IconButton(onClick = { navController.navigate("about") }) {
                Icon(
                    imageVector = Icons.Filled.Info, // Material Icon for Help
                    contentDescription = "About Us",
                    tint = Color.Black
                )
            }
            // Profile Icon Button
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Filled.Person, // Material Icon for Profile
                    contentDescription = "Profile",
                    tint = Color.Black
                )
            }
        }
    }
}


@Composable
fun MealItem(meal: Meal) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = meal.food_name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${meal.calories} Calories",
            fontSize = 14.sp
        )
    }
}

@Composable
fun LottieAnimationView(
    modifier: Modifier = Modifier,
    animationRes: Int
) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier,
        factory = { createLottieView(context, animationRes) }
    )
}

private fun createLottieView(context: Context, animationRes: Int): LottieAnimationView {
    return LottieAnimationView(context).apply {
        setAnimation(animationRes)
        playAnimation()
        repeatCount = LottieDrawable.INFINITE
    }
}
