package com.company.noteskotlin.todo


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun TodoCard(
    todo: TodoEntity,
    onCheckedChange: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = {
                    onCheckedChange()
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration =
                        if (todo.isCompleted)
                            TextDecoration.LineThrough
                        else
                            TextDecoration.None
                )

                if (todo.description.isNotBlank()) {

                    Text(
                        text = todo.description,
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration =
                            if (todo.isCompleted)
                                TextDecoration.LineThrough
                            else
                                TextDecoration.None
                    )

                }

            }

            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Todo"
                )

            }

        }

    }

}