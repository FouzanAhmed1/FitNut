package com.example.fitnut

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // App Title
            Text(
                text = "FitNut",
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fitness Tracking Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { navController.navigate("fitness") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Fitness Tracking",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nutrition Guidance Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { navController.navigate("nutrition") },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Nutrition Guidance",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
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