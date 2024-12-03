package com.example.fitnut

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import com.example.fitnut.R // Make sure the font is referenced correctly

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WelcomeScreen()
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    // Reference to the custom Italiana font (make sure italiana.ttf is placed in res/font folder)
    val italianaFont = FontFamily(Font(R.font.italiana)) // Make sure this font exists in res/font folder

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Set background color to white
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // Welcome Text with Italiana font, large size, and centered
            Text(
                text = "Welcome\n to\n FitNut",
                fontSize = 96.sp, // Large text size
                fontWeight = FontWeight.Normal,
                color = Color.Black, // Black text color
                textAlign = TextAlign.Center,
                fontFamily = italianaFont, // Custom font
                lineHeight = 110.sp, // Add some line height for spacing between lines
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Segmented Buttons (Sign In and Sign Up)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Sign In Button with a black border and tick icon
                Button(
                    onClick = { /* Navigate to Sign In */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, // Black background for Sign In
                        contentColor = Color.White // White text color
                    ),
                    shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                    modifier = Modifier
                        .weight(1f)
                        //.border(2.dp, Color.White, RoundedCornerShape(16.dp)) // Add black border
                ) {
                    // Tick icon and Sign In text
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = "Check",
                        tint = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "Sign In", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.width(6.dp)) // Space between buttons

                // Sign Up Button with a white background and black border
                Button(
                    onClick = { /* Navigate to Sign Up */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, // White background for Sign Up
                        contentColor = Color.White// Black text color
                    ),
                    shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                    modifier = Modifier
                        .weight(1f)
                        //.border(2.dp, Color.White, RoundedCornerShape(16.dp)) // Add black border
                ) {
                    Text(text = "Sign Up", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                }
            }

            // Right arrow below the segmented buttons
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "Arrow Forward",
                tint = Color.Black,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
