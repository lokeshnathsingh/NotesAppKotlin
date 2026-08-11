package com.company.noteskotlin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Notes)
    @Update
    suspend fun updateNote(note: Notes)
    @Delete
    suspend fun deleteNote(note: Notes)
    @Query("SELECT * FROM notes ORDER BY timeStamp DESC")
    fun getAllNotes(): Flow<List<Notes>>

}