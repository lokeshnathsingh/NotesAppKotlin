package com.company.noteskotlin.todo

class TodoRepository(
    private val todoDao: TodoDao
) {

    val allTodos = todoDao.getAllTodos()

    suspend fun insertTodo(todo: TodoEntity) {
        todoDao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: TodoEntity) {
        todoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: TodoEntity) {
        todoDao.updateTodo(todo)
    }
}