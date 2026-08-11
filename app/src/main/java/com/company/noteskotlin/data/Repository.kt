package com.company.noteskotlin.data

import kotlinx.coroutines.flow.Flow

class Repository(private val notesDao: NotesDao) {
    val allNotes: Flow<List<Notes>> = notesDao.getAllNotes()
    suspend fun insertNote(note: Notes) {
        notesDao.insertNote(note)
    }
    suspend fun updateNote(note: Notes) {
        notesDao.updateNote(note)
    }
    suspend fun deleteNote(note: Notes) {
        notesDao.deleteNote(note)
    }
}

