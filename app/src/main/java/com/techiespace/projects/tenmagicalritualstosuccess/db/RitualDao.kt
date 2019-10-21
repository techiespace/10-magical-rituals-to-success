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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ritual: Ritual)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMany(rituals: List<Ritual>)

    @Query("DELETE FROM rituals")
    suspend fun deleteAll()
}