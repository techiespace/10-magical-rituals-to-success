package com.techiespace.projects.tenmagicalritualstosuccess.repositoies

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import com.techiespace.projects.tenmagicalritualstosuccess.db.HabitDao

class HabitRepository(private val habitDao: HabitDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allHabits: LiveData<List<Habit>> = habitDao.getAllHabits()

    @WorkerThread
    suspend fun update(todo: Habit) {
        habitDao.update(todo)
    }

    suspend fun dummyHabitToggle() {
        habitDao.dummyHabitToggle()
    }

    suspend fun getActiveHabitsCount(ritualId: Int): Int {
        return habitDao.getActiveHabitsCount(ritualId)
    }

}