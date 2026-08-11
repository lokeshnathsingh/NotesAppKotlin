package com.company.noteskotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.noteskotlin.todo.TodoEntity
import com.company.noteskotlin.todo.TodoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val todos: StateFlow<List<TodoEntity>> =
        repository.allTodos
            .map { it.sortedByDescending { todo -> todo.timeStamp } }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun addTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.insertTodo(todo)
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }
}