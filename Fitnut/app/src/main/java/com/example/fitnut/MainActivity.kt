package com.example.fitnut

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnut.ui.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.common.api.ApiException


class MainActivity : ComponentActivity() {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Google Sign-In client
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_CLIENT_ID")  // Replace with your OAuth Client ID
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Initialize the permission request launcher
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            val steps = mutableStateOf(0)
            val caloriesBurned = mutableStateOf(0f)
            if (isGranted) {
                // Permission granted, fetch fitness data
                fetchFitnessData(steps, caloriesBurned)
            } else {
                // Permission denied
                Log.e("MainActivity", "Permission for ACTIVITY_RECOGNITION was denied.")
            }
        }

        // Set up the UI
        setContent {
            val steps = remember { mutableStateOf(0) }
            val caloriesBurned = remember { mutableStateOf(0f) }

            // Check and request permissions
            checkAndRequestPermissions(steps, caloriesBurned)

            // Set up the app UI
            FitNutAppUI(steps.value, caloriesBurned.value)
        }
    }

    // Check and request runtime permission for ACTIVITY_RECOGNITION
    private fun checkAndRequestPermissions(
        steps: MutableState<Int>,
        caloriesBurned: MutableState<Float>
    ) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permission using the launcher
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        } else {
            // Permission already granted, fetch fitness data
            fetchFitnessData(steps, caloriesBurned)
        }
    }

    // Fetch fitness data directly from Google Fit API
    private fun fetchFitnessData(steps: MutableState<Int>, caloriesBurned: MutableState<Float>) {
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.TYPE_CALORIES_EXPENDED, FitnessOptions.ACCESS_READ)
            .build()

        val account = GoogleSignIn.getAccountForExtension(this, fitnessOptions)

        // Fetch steps
        Fitness.getHistoryClient(this, account)
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener { dataSet ->
                steps.value =
                    if (dataSet.isEmpty) 0 else dataSet.dataPoints[0].getValue(Field.FIELD_STEPS)
                        .asInt()
                Log.d("FitnessAPI", "Total steps: ${steps.value}")
            }
            .addOnFailureListener { e ->
                Log.e("FitnessAPI", "Failed to read steps", e)
            }

        // Fetch calories burned
        Fitness.getHistoryClient(this, account)
            .readDailyTotal(DataType.TYPE_CALORIES_EXPENDED)
            .addOnSuccessListener { dataSet ->
                caloriesBurned.value =
                    if (dataSet.isEmpty) 0f else dataSet.dataPoints[0].getValue(Field.FIELD_CALORIES)
                        .asFloat()
                Log.d("FitnessAPI", "Total calories burned: ${caloriesBurned.value}")
            }
            .addOnFailureListener { e ->
                Log.e("FitnessAPI", "Failed to read calories", e)
            }
    }

    // Handle Google Sign-In result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                val auth = FirebaseAuth.getInstance()
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            Log.d("GoogleSignIn", "Sign-in successful: ${user?.displayName}")
                            // You can navigate to the home screen here
                        } else {
                            Log.e("GoogleSignIn", "Sign-in failed", task.exception)
                        }
                    }
            } catch (e: ApiException) {
                Log.e("GoogleSignIn", "Sign-in failed", e)
            }
        }
    }

    @Composable
    fun FitNutAppUI(steps: Int, caloriesBurned: Float) {
        val navController = rememberNavController()
        Scaffold(
            content = { innerPadding ->
                Navigation(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    steps = steps,
                    caloriesBurned = caloriesBurned
                )
            }
        )
    }

    @Composable
    fun Navigation(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        steps: Int,
        caloriesBurned: Float
    ) {
        NavHost(navController = navController, startDestination = "welcome", modifier = modifier) {
            composable("home") { HomeScreen(navController = navController, modifier = modifier) }
            composable("nutrition") {
                NutritionScreen(
                    navController = navController,
                    modifier = modifier
                )
            }
            composable("fitness") {
                FitnessScreen(
                    navController = navController,
                    modifier = modifier,
                    steps = steps,
                    caloriesBurned = caloriesBurned
                )
            }
            composable("progress") {
                ProgressScreen(
                    navController = navController,
                    modifier = modifier
                )
            }
            composable("welcome") { WelcomeActivity(navController) }
            composable("sign_in") { SignInPage(navController = navController) }
            composable("sign_up") { SignUpPage(navController = navController) }
            composable("profile") { ProfileScreen(navController = navController) }
        }
    }
}
