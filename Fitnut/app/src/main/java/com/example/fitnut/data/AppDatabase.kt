package com.example.fitnut.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserProgress::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProgressDao(): UserProgressDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun initDatabase(context: Context) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "fitnut_db"
            ).build()
        }

        fun getInstance(): AppDatabase = INSTANCE!!
    }
}
