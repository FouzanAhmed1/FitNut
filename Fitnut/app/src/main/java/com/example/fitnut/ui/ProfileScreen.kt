package com.example.fitnut.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val firestore = FirebaseFirestore.getInstance()
    val coroutineScope = rememberCoroutineScope()

    // States for user inputs
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var waterIntakeGoal by remember { mutableStateOf("") }
    var dailyCalGoal by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weeklyCalGoal by remember { mutableStateOf("") }

    // Load existing data from Firestore
    LaunchedEffect(Unit) {
        try {
            val document = firestore.collection("profiles").document("userProfile").get().await()
            if (document.exists()) {
                fullName = document.getString("fullName") ?: ""
                email = document.getString("email") ?: ""
                age = document.getString("age") ?: ""
                gender = document.getString("gender") ?: ""
                weight = document.getString("weight") ?: ""
                height = document.getString("height") ?: ""
                waterIntakeGoal = document.getString("waterIntakeGoal") ?: ""
                dailyCalGoal = document.getString("dailyCalGoal") ?: ""
                weeklyCalGoal = document.getString("weeklyCalGoal") ?: ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Title
            Text(
                text = "Profile",
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Form Fields
            ProfileTextField(label = "Full Name", value = fullName, onValueChange = { fullName = it })
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Email", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Age", value = age, onValueChange = { age = it }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Gender", value = gender, onValueChange = { gender = it })
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Weight (kg)", value = weight, onValueChange = { weight = it }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Height (inches)", value = height, onValueChange = { height = it }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Water Intake Goal (liters)", value = waterIntakeGoal, onValueChange = { waterIntakeGoal = it }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Daily Calories Goal", value = dailyCalGoal, onValueChange = { dailyCalGoal = it }, keyboardType = KeyboardType.Number)
            Spacer(modifier = Modifier.height(8.dp))
            ProfileTextField(label = "Weekly Calories Goal", value = weeklyCalGoal, onValueChange = { weeklyCalGoal = it }, keyboardType = KeyboardType.Number)

            Spacer(modifier = Modifier.height(16.dp))

            // Save Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        saveProfileData(
                            firestore,
                            fullName,
                            email,
                            age,
                            gender,
                            weight,
                            height,
                            waterIntakeGoal,
                            dailyCalGoal,
                            weeklyCalGoal
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(text = "Save Profile", color = Color.White, fontSize = 16.sp)
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
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = Color.Black
                )
            }
            // About Icon Button
            IconButton(onClick = { navController.navigate("about") }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "About Us",
                    tint = Color.Black
                )
            }
            // Profile Icon Button
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = Color.Black
                )
            }
        }
    }
}

suspend fun saveProfileData(
    firestore: FirebaseFirestore,
    fullName: String,
    email: String,
    age: String,
    gender: String,
    weight: String,
    height: String,
    waterIntakeGoal: String,
    dailyCalGoal: String,
    weeklyCalGoal: String
) {
    val profileData = hashMapOf(
        "fullName" to fullName,
        "email" to email,
        "age" to age,
        "gender" to gender,
        "weight" to weight,
        "height" to height,
        "waterIntakeGoal" to waterIntakeGoal,
        "dailyCalGoal" to dailyCalGoal,
        "weeklyCalGoal" to weeklyCalGoal
    )

    try {
        firestore.collection("profiles").document("userProfile").set(profileData).await()
        println("Profile saved successfully")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

@Composable
fun ProfileTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
