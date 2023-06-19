package com.example.data.utils

import com.example.notetakingapp.models.Note

object NoteValidation {
    fun validateNote(note: Note): Result<Unit> = runCatching {
        check(note.title.isNotEmpty() && note.content.isNotEmpty()) {
            "Note must contain title and content"
        }
    }
}