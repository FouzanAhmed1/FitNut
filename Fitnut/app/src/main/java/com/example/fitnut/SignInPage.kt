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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SignInPage() {
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
            Text(text = "Enter your email and password to sign in.", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Email") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = "", onValueChange = {}, label = { Text("Password") })
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),)
            {
                Text(text = "Sign In", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyComposablePreview() {
    SignInPage()
}

