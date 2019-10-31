package com.techiespace.projects.tenmagicalritualstosuccess

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import com.techiespace.projects.tenmagicalritualstosuccess.db.HabitDao

class HabitRepository(private val habitDao: HabitDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Habit>> = habitDao.getAlphabetizedWords()

    suspend fun insert(word: Habit) {
        habitDao.insert(word)
    }

    @WorkerThread
    suspend fun update(todo: Habit) {
        habitDao.update(todo)
    }
}