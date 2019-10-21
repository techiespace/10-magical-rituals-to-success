package com.techiespace.projects.tenmagicalritualstosuccess

import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Ritual
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: RitualDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Ritual>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Ritual) {
        wordDao.insert(word)
    }
}