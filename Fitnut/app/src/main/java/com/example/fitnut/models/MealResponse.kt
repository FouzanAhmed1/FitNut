package com.example.fitnut.models

data class MealResponse(
    val hits: List<Meal>
)

data class Meal(
    val food_name: String,
    val serving_qty: Int,
    val serving_unit: String,
    val calories: Double
)
