package com.techiespace.projects.tenmagicalritualstosuccess.repositoies

import androidx.lifecycle.LiveData
import com.techiespace.projects.tenmagicalritualstosuccess.db.Ritual
import com.techiespace.projects.tenmagicalritualstosuccess.db.RitualDao

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class RitualRepository(private val ritualDao: RitualDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allRituals: LiveData<List<Ritual>> = ritualDao.getAllRituals()

    val lockedRituals: LiveData<List<Int>> = ritualDao.getLockedRituals()

    val activeRitualIds: LiveData<List<Int>> = ritualDao.getActiveRitualIds()

    suspend fun insert(word: Ritual) {
        ritualDao.insert(word)
    }

    suspend fun unlock(ritualId: Int) {
        ritualDao.unlock(ritualId)
    }
}