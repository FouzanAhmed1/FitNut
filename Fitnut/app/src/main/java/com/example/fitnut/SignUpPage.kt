package com.example.fitnut

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font

@Composable
fun SignUpPage() {
    val italianaFont = FontFamily(Font(R.font.italiana))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "FitNut",
                fontSize = 96.sp,
                fontFamily = italianaFont,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Create an account to get started.", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Name") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })
            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { /* Perform Sign-Up */ }) {
                Text("Continue")
            }
        }
    }
}
