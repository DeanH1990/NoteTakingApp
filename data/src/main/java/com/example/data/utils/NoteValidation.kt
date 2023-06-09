package com.example.data.utils

import com.example.notetakingapp.models.Note

object NoteValidation {
    fun validateNote(note: Note) {
        if (note.title.isEmpty() || note.content.isEmpty()) {
            throw IllegalArgumentException("Note must contain title and content")
        }
    }
}