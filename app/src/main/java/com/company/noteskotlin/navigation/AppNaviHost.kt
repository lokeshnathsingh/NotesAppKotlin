package com.company.noteskotlin.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.company.noteskotlin.data.Notes
import com.company.noteskotlin.notesui.AddNoteScreen
import com.company.noteskotlin.notesui.AddTodoScreen
import com.company.noteskotlin.notesui.NoteDetailScreen
import com.company.noteskotlin.notesui.NotesScreen
import com.company.noteskotlin.notesui.SettingsScreen
import com.company.noteskotlin.notesui.TodoScreen
import com.company.noteskotlin.todo.TodoEntity

sealed class Screen(val route: String) {

    object Notes : Screen("notes")

    object AddNote : Screen("add_note")

    object NoteDetail : Screen("note_detail/{noteId}") {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }

    object Todo : Screen("todo")

    object AddTodo : Screen("add_todo")

    object Settings : Screen("settings")
}

@Composable
fun AppNavHost(
    navController: NavHostController,

    notes: List<Notes>,
    todos: List<TodoEntity>,

    onAddNote: (Notes) -> Unit,
    onDeleteNote: (Notes) -> Unit,
    onUpdateNote: (Notes) -> Unit,

    onAddTodo: (TodoEntity) -> Unit,
    onDeleteTodo: (TodoEntity) -> Unit,
    onUpdateTodo: (TodoEntity) -> Unit,

    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,

    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route,
        modifier = modifier
    ) {
        composable(Screen.Settings.route) {
            SettingsScreen(
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange,
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Notes.route) {

            NotesScreen(
                notes = notes,
                onAddNoteClick = {
                    navController.navigate(Screen.AddNote.route)
                },
                onNoteClick = { note ->
                    navController.navigate(
                        Screen.NoteDetail.createRoute(note.id)
                    )
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        composable(Screen.AddNote.route) {

            AddNoteScreen(
                onSaveNote = {
                    onAddNote(it)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.NoteDetail.route,
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val noteId = backStackEntry.arguments?.getInt("noteId")
            val note = notes.find { it.id == noteId }

            if (note != null) {

                NoteDetailScreen(
                    note = note,
                    onBack = {
                        navController.popBackStack()
                    },
                    onDelete = {
                        onDeleteNote(note)
                        navController.popBackStack()
                    },
                    onSave = {
                        onUpdateNote(it)
                        navController.popBackStack()
                    }
                )

            } else {

                Text("Note not found")

            }
        }

        composable(Screen.Todo.route) {

            TodoScreen(
                todos = todos,
                onAddTodo = onAddTodo,
                onDeleteTodo = onDeleteTodo,
                onUpdateTodo = onUpdateTodo,
                onAddClick = {
                    navController.navigate(Screen.AddTodo.route)
                }
            )
        }

        composable(Screen.AddTodo.route) {

            AddTodoScreen(
                onSaveTodo = {
                    onAddTodo(it)
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}