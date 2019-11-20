package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDao {

    @Query("SELECT * from habits WHERE ritual_id IN(:active_rituals)")
    fun getActiveHabits(active_rituals: Array<Int>): List<Habit>

    @Query("SELECT COUNT(*) from habits WHERE ritual_id = (:active_ritual)")
    suspend fun getActiveHabitsCount(active_ritual: Int): Int

    @Query("SELECT * from habits ORDER BY habit_id ASC")
    fun getAllHabits(): LiveData<List<Habit>>

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

    @Query("UPDATE habits SET done = 1")
    suspend fun dummyHabitToggle()    //hack to force run observer
}