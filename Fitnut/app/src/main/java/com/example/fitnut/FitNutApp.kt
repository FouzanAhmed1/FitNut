package com.example.fitnut

import android.app.Application
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.example.fitnut.data.AppDatabase

class FitNutApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        // Room Database initialization
        AppDatabase.initDatabase(this)
    }
}
