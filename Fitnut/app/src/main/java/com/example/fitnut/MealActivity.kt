package com.example.fitnut

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.fitnut.adapters.MealAdapter
import com.example.fitnut.api.NutritionixService
import com.example.fitnut.models.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loaderAnimationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        recyclerView = findViewById(R.id.recyclerView)
        loaderAnimationView = findViewById(R.id.loaderAnimationView)

        // Start loading animation
        loaderAnimationView.visibility = View.VISIBLE
        loaderAnimationView.playAnimation()

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://trackapi.nutritionix.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create Nutritionix API service
        val service = retrofit.create(NutritionixService::class.java)

        // Make API call to get meals
        val call = service.getMeals("34165b76", "5d6f1ce010a843474cfc3b0e3a8cf0db", "chicken")

        call.enqueue(object : Callback<MealResponse> {
            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                loaderAnimationView.visibility = View.GONE

                if (response.isSuccessful) {
                    val meals = response.body()?.hits ?: emptyList()
                    recyclerView.adapter = MealAdapter(meals)
                } else {
                    Toast.makeText(this@MealActivity, "Failed to fetch meals", Toast.LENGTH_SHORT).show()
                    Log.e("MealActivity", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                loaderAnimationView.visibility = View.GONE
                Toast.makeText(this@MealActivity, "Network error", Toast.LENGTH_SHORT).show()
                Log.e("MealActivity", "Failure: ${t.message}")
            }
        })
    }
}
