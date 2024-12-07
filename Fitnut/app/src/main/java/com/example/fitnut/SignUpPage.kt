package com.example.fitnut

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
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun SignUpPage(navController: NavController) {
    val italianaFont = FontFamily(Font(R.font.italiana))

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val signUpMessage = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()


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

            OutlinedTextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        val success = signUp(auth, firestore, name.value, email.value, password.value, signUpMessage)
                        if (success) {
                            // Navigate to Sign-In Page
                            navController.navigate("sign_in")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Sign Up", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = signUpMessage.value, color = Color.Red, fontSize = 14.sp)
        }
    }
}

suspend fun signUp(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    name: String,
    email: String,
    password: String,
    signUpMessage: MutableState<String>
): Boolean {
    if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
        signUpMessage.value = "Please fill out all fields."
        return false
    }
    return try {
        // Create a user with Firebase Authentication
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid
        if (uid != null) {
            // Save user data to Firestore
            val user = hashMapOf(
                "uid" to uid,
                "name" to name,
                "email" to email
            )
            firestore.collection("users").document(uid).set(user).await()
            signUpMessage.value = "Sign up successful!"
            true
        } else {
            signUpMessage.value = "Failed to retrieve user ID."
            false
        }
    } catch (e: Exception) {
        if (e.message?.contains("network error", ignoreCase = true) == true) {
            signUpMessage.value = "Network error occurred. Please try again later."
        } else {
            signUpMessage.value = "Error: ${e.message}"
        }
        false
    }
}

