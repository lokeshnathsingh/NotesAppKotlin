package com.company.noteskotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.company.noteskotlin.data.NotesDb
import com.company.noteskotlin.data.Repository
import com.company.noteskotlin.navigation.AppNavHost
import com.company.noteskotlin.notesui.BottomNavBar
import com.company.noteskotlin.todo.TodoRepository
import com.company.noteskotlin.ui.theme.NotesKotlinTheme
import com.company.noteskotlin.viewmodel.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = NotesDb.database(applicationContext)

        val notesRepository = Repository(database.notesDao())
        val notesViewModel = ViewModelProvider(
            this,
            NotesViewModelFactory(notesRepository)
        )[NotesViewModel::class.java]

        val todoRepository = TodoRepository(database.todoDao())
        val todoViewModel = ViewModelProvider(
            this,
            TodoViewModelFactory(todoRepository)
        )[TodoViewModel::class.java]

        setContent {

            val notes by notesViewModel.notes.collectAsState()
            val todos by todoViewModel.todos.collectAsState()

            var isDarkTheme by rememberSaveable {
                mutableStateOf(false)
            }

            val navController = rememberNavController()

            NotesKotlinTheme(
                darkTheme = isDarkTheme
            ) {

                Scaffold(
                    bottomBar = {
                        BottomNavBar(navController)
                    },
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets(0)
                ) { padding ->

                    AppNavHost(
                        navController = navController,

                        notes = notes,
                        todos = todos,

                        onAddNote = notesViewModel::addNote,
                        onDeleteNote = notesViewModel::deleteNote,
                        onUpdateNote = notesViewModel::updateNote,

                        onAddTodo = todoViewModel::addTodo,
                        onDeleteTodo = todoViewModel::deleteTodo,
                        onUpdateTodo = todoViewModel::updateTodo,

                        isDarkTheme = isDarkTheme,
                        onThemeChange = {
                            isDarkTheme = it
                        },

                        modifier = Modifier.padding(
                            bottom = padding.calculateBottomPadding()
                        )
                    )
                }
            }
        }
    }
}