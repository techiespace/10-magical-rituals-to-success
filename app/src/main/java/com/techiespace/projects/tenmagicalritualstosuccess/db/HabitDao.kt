package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HabitDao {

    @Query("SELECT * from habits ORDER BY habit_id ASC")
    fun getAlphabetizedWords(): List<Habit>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMany(habits: List<Habit>)

    @Query("DELETE FROM habits")
    suspend fun deleteAll()
}