package com.company.noteskotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.noteskotlin.data.Repository
import kotlin.jvm.java

class NotesViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}