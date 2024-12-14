package com.example.fitnut.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AboutContactScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // About Us Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "About Us",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "FitNut is a comprehensive platform designed to help individuals achieve their health and fitness goals. We believe in empowering everyone to live a healthier and happier life. Whether you're tracking calories, managing hydration, or striving for your fitness milestones, FitNut is here to guide you every step of the way.",
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Us Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Contact Us",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "We would love to hear from you! Reach out to us through the following:",
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Contact Information
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        ContactInfo(label = "Email", value = "support@fitnut.com")
                        Spacer(modifier = Modifier.height(8.dp))
                        ContactInfo(label = "Phone", value = "+1 (123) 456-7890")
                        Spacer(modifier = Modifier.height(8.dp))
                        ContactInfo(label = "Address", value = "123 FitNut Street, Wellness City, USA")
                    }
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
            // About Icon Button
            IconButton(onClick = { navController.navigate("about") }) {
                Icon(
                    imageVector = Icons.Filled.Info, // Material Icon for About
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
fun ContactInfo(label: String, value: String) {
    Column {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutContactScreenPreview() {
    AboutContactScreen(navController = rememberNavController())
}

