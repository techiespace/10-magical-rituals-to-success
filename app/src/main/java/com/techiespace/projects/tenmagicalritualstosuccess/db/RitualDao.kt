package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RitualDao {

    @Query("SELECT * from rituals ORDER BY ritual_id ASC")
    fun getAlphabetizedWords(): LiveData<List<Ritual>>

    @Query("SELECT ritual_id from rituals WHERE locked = 1")
    fun getLockedRituals(): LiveData<List<Int>>

    @Query("SELECT ritual_id from rituals WHERE locked = 0")
    fun getActiveRitualIds(): LiveData<List<Int>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ritual: Ritual)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMany(rituals: List<Ritual>)

    @Query("UPDATE rituals SET locked = 0 WHERE ritual_id = :ritual_id")
    suspend fun unlock(ritual_id: Int)

    @Query("DELETE FROM rituals")
    suspend fun deleteAll()
}