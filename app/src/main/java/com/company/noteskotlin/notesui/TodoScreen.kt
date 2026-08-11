package com.company.noteskotlin.notesui


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.company.noteskotlin.todo.TodoCard
import com.company.noteskotlin.todo.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todos: List<TodoEntity>,
    onAddTodo: (TodoEntity) -> Unit,
    onDeleteTodo: (TodoEntity) -> Unit,
    onUpdateTodo: (TodoEntity) -> Unit,
    onAddClick: () -> Unit = {}
) {

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("My Todos")
                }
            )

        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Todo"
                )
            }

        }

    ) { padding ->

        if (todos.isEmpty()) {

            EmptyTodoScreen(
                modifier = Modifier.padding(padding)
            )

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentPadding = PaddingValues(12.dp),

                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

                items(todos) { todo ->

                    TodoCard(

                        todo = todo,

                        onCheckedChange = {

                            onUpdateTodo(
                                todo.copy(
                                    isCompleted = !todo.isCompleted
                                )
                            )

                        },

                        onDelete = {

                            onDeleteTodo(todo)

                        }

                    )

                }

            }

        }

    }

}

@Composable
private fun EmptyTodoScreen(
    modifier: Modifier = Modifier
) {

    androidx.compose.foundation.layout.Column(

        modifier = modifier.fillMaxSize(),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Icon(
            imageVector = Icons.Default.CheckCircleOutline,
            contentDescription = null
        )

        Text(
            text = "No Todos Yet",
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = "Tap + to create your first task."
        )

    }

}