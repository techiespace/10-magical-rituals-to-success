package com.techiespace.projects.tenmagicalritualstosuccess

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Habit
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDatabase
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

    val calendar = Calendar.getInstance()
    val date = calendar.get(Calendar.DATE)
    val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
    val year = calendar.get(Calendar.YEAR)
    val complete_date = "$date $month $year"

    init {
        val todoDao = RitualDatabase.getDatabase(application, scope).habitDao()
        val ritualDao = RitualDatabase.getDatabase(application, scope).ritualDao()
        todoRepository = HabitRepository(todoDao)
        todos = todoRepository.allWords

        ritualRepository = RitualRepository(ritualDao)
        lockedRituals = ritualRepository.lockedRituals
    }

    fun insert(todo: Habit) = scope.launch(Dispatchers.IO) {
        todoRepository.insert(todo)
    }

    fun toggleDone(todo: Habit, checked: Boolean) = scope.launch(Dispatchers.IO) {
        //TODO: Handle cases for 2021 when no separator might cause a problem
        if (complete_date !in todo.timestamps) {
            todo.timestamps = todo.timestamps + complete_date
        } else
            todo.timestamps = todo.timestamps.replace(complete_date, "").trim()
        todoRepository.update(todo)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}