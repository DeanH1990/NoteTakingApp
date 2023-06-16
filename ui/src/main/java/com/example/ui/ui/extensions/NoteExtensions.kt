package com.example.ui.ui.extensions

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.models.TextNote
import com.example.ui.ui.model.NoteUiState

fun Note.toNoteUiState(): NoteUiState = NoteUiState(
    id = id,
    title = title,
    content = content,
)

fun NoteUiState.toNote(): Note = TextNote(
    id = id,
    title = title,
    content = content
)

fun NoteUiState.isValid(): Boolean {
    return title.isNotBlank() && content.isNotBlank()
}