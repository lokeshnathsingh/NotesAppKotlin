package com.company.noteskotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.noteskotlin.data.Notes
import com.company.noteskotlin.data.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(private val repository: Repository): ViewModel(){

    val notes: StateFlow<List<Notes>> = repository.allNotes.map {
        it.sortedByDescending { note ->  note.timeStamp }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList()
    )

    fun addNote(note: Notes){
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun deleteNote(note: Notes){
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun updateNote(note: Notes){
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }
}