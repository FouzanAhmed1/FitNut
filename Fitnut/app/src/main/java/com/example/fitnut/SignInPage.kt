package com.example.fitnut

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.navigation.compose.rememberNavController


@Composable
fun SignInPage(navController: NavController) {
    val italianaFont = FontFamily(Font(R.font.italiana))

    // States for email, password, and error message
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val signInMessage = remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val coroutineScope = rememberCoroutineScope()

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

            // Email input
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Password input
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Sign In button
            Button(
                onClick = {
                    coroutineScope.launch {
                        signIn(auth, email.value, password.value, signInMessage, navController)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Sign In", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = signInMessage.value, color = Color.Red, fontSize = 14.sp)
        }
    }
}

suspend fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    signInMessage: MutableState<String>,
    navController: NavController
) {
    if (email.isEmpty() || password.isEmpty()) {
        signInMessage.value = "Please fill out all fields."
        return
    }

    try {
        // Sign in with Firebase Authentication
        val result = auth.signInWithEmailAndPassword(email, password).await()

        if (result.user != null) {
            // On successful sign in, navigate to home screen
            navController.navigate("home")
        }
    } catch (e: Exception) {
        signInMessage.value = "Error: ${e.message}"
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPagePreview() {
    // Dummy NavController for preview
    val navController = rememberNavController()
    SignInPage(navController = navController)
}
