package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {
    fun execute(note: Note) {
        noteRepository.updateNote(note)
    }
}