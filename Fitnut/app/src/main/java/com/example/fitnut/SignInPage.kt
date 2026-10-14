package com.example.fitnut

import android.app.Activity
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.ui.platform.LocalContext

@Composable
fun SignInPage(navController: NavController) {
    val italianaFont = FontFamily(Font(R.font.italiana))

    // States for email, password, and error message
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val signInMessage = remember { mutableStateOf("") }

    val auth = FirebaseAuth.getInstance()
    val coroutineScope = rememberCoroutineScope()

    // Get the current context for Google Sign-In client initialization
    val context = LocalContext.current
    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)  // Set via gradle.properties, see README
            .requestEmail()
            .build()

        GoogleSignIn.getClient(context, gso)
    }

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

            // Sign In button for email/password
            Button(
                onClick = {
                    coroutineScope.launch {
                        signInWithEmailPassword(auth, email.value, password.value, signInMessage, navController)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Sign In with Email", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Google Sign-In button
            Button(
                onClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    (context as? Activity)?.startActivityForResult(signInIntent, 1000)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Sign In with Google", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = signInMessage.value, color = Color.Red, fontSize = 14.sp)
        }
    }
}

// Function for Email/Password sign-in
suspend fun signInWithEmailPassword(
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
        val result = auth.signInWithEmailAndPassword(email, password).await()
        if (result.user != null) {
            navController.navigate("home")
        }
    } catch (e: Exception) {
        signInMessage.value = "Error: ${e.message}"
    }
}
