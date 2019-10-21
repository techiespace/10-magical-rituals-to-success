package com.techiespace.projects.tenmagicalritualstosuccess.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rituals")
data class Ritual(
    @PrimaryKey @ColumnInfo(name = "ritual_id") val id: Int,
    @ColumnInfo(name = "locked") val locked: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "desc") val desc: String,
    @ColumnInfo(name = "article") val article: String
)