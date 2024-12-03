package com.example.fitnut.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserProgressDao {

    @Insert
    suspend fun insert(userProgress: UserProgress)

    @Query("SELECT * FROM UserProgress WHERE id = :id")
    suspend fun getUserProgressById(id: Int): UserProgress?

    @Query("SELECT * FROM UserProgress")
    suspend fun getAllUserProgress(): List<UserProgress>

    @Query("DELETE FROM UserProgress")
    suspend fun deleteAll()
}
