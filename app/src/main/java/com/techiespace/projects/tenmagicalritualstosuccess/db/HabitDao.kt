package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {

    @Query("SELECT * from habits WHERE ritual_id IN(:active_rituals)")
    fun getActiveHabits(active_rituals: Array<Int>): List<Habit>

    @Query("SELECT COUNT(*) from habits WHERE ritual_id IN(:active_rituals)")
    fun getActiveHabitsCount(active_rituals: Array<Int>): Int

    @Query("SELECT * from habits ORDER BY habit_id ASC")
    fun getAlphabetizedWords(): LiveData<List<Habit>>

    @Query("SELECT * from habits WHERE ritual_id = :ritual_id")
    fun getHabitsByRituals(ritual_id: Int): List<Habit>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habit: Habit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMany(habits: List<Habit>)

    @Update
    suspend fun update(todo: Habit)

    @Query("DELETE FROM habits")
    suspend fun deleteAll()
}