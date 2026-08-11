package com.company.noteskotlin.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTodo(todo: TodoEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todo: TodoEntity)

    @Query("SELECT * FROM todo_table")
    fun getAllTodos(): Flow<List<TodoEntity>>
}