package com.company.noteskotlin.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val timeStamp: Long = System.currentTimeMillis(),
    val isLocked: Boolean = false
)
