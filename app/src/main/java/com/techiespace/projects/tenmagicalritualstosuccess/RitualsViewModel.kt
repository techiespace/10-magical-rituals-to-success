package com.techiespace.projects.tenmagicalritualstosuccess

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.techiespace.projects.tenmagicalritualstosuccess.db.Ritual
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDatabase
import kotlinx.coroutines.launch

// Class extends AndroidViewModel and requires application as a parameter.
class RitualsViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: RitualRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Ritual>>
    val lockedRituals: LiveData<List<Int>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = RitualDatabase.getDatabase(application, viewModelScope).ritualDao()
        repository = RitualRepository(wordsDao)
        allWords = repository.allWords
        lockedRituals = repository.lockedRituals
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(word: Ritual) = viewModelScope.launch {
        repository.insert(word)
    }
}