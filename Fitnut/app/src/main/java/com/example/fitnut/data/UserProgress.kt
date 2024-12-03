package com.example.fitnut.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProgress(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val steps: Int,
    val caloriesBurned: Int,
    val caloriesConsumed: Int
)
