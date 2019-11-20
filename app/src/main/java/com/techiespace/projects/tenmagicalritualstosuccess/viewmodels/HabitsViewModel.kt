package com.techiespace.projects.tenmagicalritualstosuccess.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDatabase
import com.techiespace.projects.tenmagicalritualstosuccess.repositoies.HabitRepository
import com.techiespace.projects.tenmagicalritualstosuccess.repositoies.RitualRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class HabitsViewModel(application: Application) : AndroidViewModel(application) {
    private val todoRepository: HabitRepository
    private val ritualRepository: RitualRepository
    val todos: LiveData<List<Habit>>
    val lockedRituals: LiveData<List<Int>>

    private var job = Job()
    private val coroutineContext: CoroutineContext get() = job + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val calendar = Calendar.getInstance()
    private val date = calendar.get(Calendar.DATE)
    private val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    private val year = calendar.get(Calendar.YEAR)
    private val completeDate = "$date $month $year"

    init {
        val todoDao = RitualDatabase.getDatabase(application, scope).habitDao()
        val ritualDao = RitualDatabase.getDatabase(application, scope).ritualDao()
        todoRepository =
            HabitRepository(todoDao)
        todos = todoRepository.allHabits

        ritualRepository =
            RitualRepository(
                ritualDao
            )
        lockedRituals = ritualRepository.lockedRituals
    }

    fun toggleDone(todo: Habit, checked: Boolean) = scope.launch(Dispatchers.IO) {
        //TODO: Handle cases for 2021 when no separator might cause a problem
        if (completeDate !in todo.timestamps) {
            todo.timestamps = todo.timestamps + completeDate + "|"
        } else
            todo.timestamps = todo.timestamps.replace("$completeDate|", "").trim()
        todoRepository.update(todo)
    }

    suspend fun getActiveHabitsCount(ritualId: Int): Int {
        return todoRepository.getActiveHabitsCount(ritualId)
    }

    fun dummyHabitToggle() = scope.launch {
        todoRepository.dummyHabitToggle()
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}