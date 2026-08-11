package com.company.noteskotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.company.noteskotlin.todo.TodoRepository

class TodoViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}