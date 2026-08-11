package com.company.noteskotlin.notesui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.company.noteskotlin.data.Notes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    notes: List<Notes>,
    onAddNoteClick: () -> Unit,
    onNoteClick: (Notes) -> Unit,
    onSettingsClick: () -> Unit
) {

    var showAboutDialog by remember { mutableStateOf(false) }
    var searchMode by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    val filteredNotes = remember(searchText, notes) {
        if (searchText.isBlank()) {
            notes
        } else {
            notes.filter {
                it.title.contains(searchText, ignoreCase = true) ||
                        it.content.contains(searchText, ignoreCase = true)
            }
        }
    }

    Scaffold(

        topBar = {

            NotesTopBar(

                searchMode = searchMode,

                searchText = searchText,

                onSearchTextChange = { searchText = it },

                onSearchClick = {

                    searchMode = !searchMode

                    if (!searchMode) {
                        searchText = ""
                    }

                },

                onSettingsClick = onSettingsClick,

                onMenuClick = {
                    showAboutDialog = true
                }

            )

        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddNoteClick,
                shape = CircleShape,
                modifier = Modifier.size(65.dp),
                containerColor = Color(0xFFE3B529),
                contentColor = Color.White
            ) {

                Text(
                    "+",
                    style = TextStyle(
                        fontSize = 55.sp,
                        fontWeight = FontWeight.Thin
                    )
                )

            }

        }

    ) { innerPadding ->

        if (filteredNotes.isEmpty()) {

            AnimatedEmptyState(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )

        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    end = 8.dp,
                    top = innerPadding.calculateTopPadding() + 8.dp,
                    bottom = innerPadding.calculateBottomPadding() + 8.dp
                ),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.Top
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(filteredNotes) { note ->

                    NoteCard(
                        note = note,
                        onClick = {
                            onNoteClick(note)
                        }
                    )

                }

            }

        }

    }

    if (showAboutDialog) {

        AlertDialog(
            onDismissRequest = {
                showAboutDialog = false
            },
            confirmButton = {

                TextButton(
                    onClick = {
                        showAboutDialog = false
                    }
                ) {
                    Text("OK")
                }

            },
            title = {
                Text("About")
            },
            text = {
                Text(
                    "Notes & To-Do App\n\nVersion 2.0.0\n\nDeveloped by\nLokesh Nath Singh"
                )
            }

        )

    }

}