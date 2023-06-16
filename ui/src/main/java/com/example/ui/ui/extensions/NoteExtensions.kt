package com.example.ui.ui.extensions

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.models.TextNote
import com.example.ui.ui.model.NoteUiState

fun Note.toNoteUiState(): NoteUiState = NoteUiState(
    id = id,
    title = title,
    content = content,
    truncatedContent = content.truncate()
)

fun NoteUiState.toNote(): Note = TextNote(
    id = id,
    title = title,
    content = content
)

fun NoteUiState.isValid(): Boolean {
    return title.isNotBlank() && content.isNotBlank()
}
private fun String.truncate(maxLength: Int = 100): String {
    return if (this.length > maxLength) this.substring(0, maxLength) + "..." else this
}