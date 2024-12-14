package com.example.fitnut.api

import com.example.fitnut.models.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NutritionixService {
    @GET("search/instant")
    fun getMeals(
        @Header("x-app-id") appId: String,
        @Header("x-app-key") appKey: String,
        @Query("query") query: String
    ): Call<MealResponse>
}
