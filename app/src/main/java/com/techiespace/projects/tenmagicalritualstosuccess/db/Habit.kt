package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey @ColumnInfo(name = "ritual_id") val habit_id: Int,
    @ColumnInfo(name = "habit_id") val ritual_id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String
)