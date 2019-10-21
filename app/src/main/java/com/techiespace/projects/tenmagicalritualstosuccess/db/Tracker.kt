package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracker", primaryKeys = ["timestamp", "ritual_id", "habit_id"])
data class Tracker(
    @PrimaryKey @ColumnInfo(name = "timestamp") val timestamp: Long,
    @PrimaryKey @ColumnInfo(name = "ritual_id") val habit_id: Int,
    @PrimaryKey @ColumnInfo(name = "habit_id") val ritual_id: Int
)