package com.example.fitnut.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // State for user inputs
    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var age by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("")) }
    var weight by remember { mutableStateOf(TextFieldValue("")) }
    var waterIntakeGoal by remember { mutableStateOf(TextFieldValue("")) }
    var dailyCalGoal by remember { mutableStateOf(TextFieldValue("")) }
    var height by remember { mutableStateOf(TextFieldValue("")) }
    var weeklyCalGoal by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Page Title
        Text(
            text = "Profile",
            fontSize = 24.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Full Name
        ProfileTextField(label = "Full Name", value = fullName, onValueChange = { fullName = it })

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        ProfileTextField(label = "Email", value = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.height(8.dp))

        // Age
        ProfileTextField(
            label = "Age",
            value = age,
            onValueChange = { age = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Gender
        ProfileTextField(label = "Gender", value = gender, onValueChange = { gender = it })

        Spacer(modifier = Modifier.height(8.dp))

        // Weight
        ProfileTextField(
            label = "Weight (kg)",
            value = weight,
            onValueChange = { weight = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Height
        ProfileTextField(
            label = "Height(inch)",
            value = height,
            onValueChange = { height = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Water Intake Goal
        ProfileTextField(
            label = "Water Intake Goal (liters)",
            value = waterIntakeGoal,
            onValueChange = { waterIntakeGoal = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Daily Activity Goal
        ProfileTextField(
            label = "Daily Calories Goal (mins)",
            value = dailyCalGoal,
            onValueChange = { dailyCalGoal = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Weekly Activity Goal
        ProfileTextField(
            label = "Weekly Calories Goal (mins)",
            value = weeklyCalGoal,
            onValueChange = { weeklyCalGoal = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                // Save user data logic here
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
}

@Composable
fun ProfileTextField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
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

// Corrected Preview Function
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
