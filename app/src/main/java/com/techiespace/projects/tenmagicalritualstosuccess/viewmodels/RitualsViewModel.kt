package com.techiespace.projects.tenmagicalritualstosuccess.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.techiespace.projects.tenmagicalritualstosuccess.db.Ritual
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDatabase
import com.techiespace.projects.tenmagicalritualstosuccess.repositoies.RitualRepository
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class RitualsViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val ritualRepository: RitualRepository
    // LiveData gives us updated words when they change.
    val allRituals: LiveData<List<Ritual>>
    val lockedRituals: LiveData<List<Int>>
    val activeRitualIds: LiveData<List<Int>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val ritualDao = RitualDatabase.getDatabase(application, viewModelScope).ritualDao()
        ritualRepository =
            RitualRepository(ritualDao)
        allRituals = ritualRepository.allRituals
        lockedRituals = ritualRepository.lockedRituals
        activeRitualIds = ritualRepository.activeRitualIds
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(word: Ritual) = viewModelScope.launch {
        ritualRepository.insert(word)
    }

    fun unlock(ritual_id: Int) = viewModelScope.launch {
        ritualRepository.unlock(ritual_id)
    }
}