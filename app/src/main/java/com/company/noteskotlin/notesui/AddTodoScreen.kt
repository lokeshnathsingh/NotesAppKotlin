package com.company.noteskotlin.notesui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.company.noteskotlin.todo.TodoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    onSaveTodo: (TodoEntity) -> Unit,
    onBack: () -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Todo")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                placeholder = { Text("Enter todo title") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                placeholder = { Text("Enter description (optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4,
                maxLines = 6,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {

                    if (title.isBlank()) return@Button

                    keyboardController?.hide()

                    onSaveTodo(
                        TodoEntity(
                            title = title.trim(),
                            description = description.trim()
                        )
                    )

                    onBack()
                }
            ) {
                Text("Save Todo")
            }
        }
    }
}