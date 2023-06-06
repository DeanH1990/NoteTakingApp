package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class GetNoteByIdUseCase(private val noteRepository: NoteRepository) {
    fun execute(id: Int): Note {
        return noteRepository.getNoteById(id)
    }
}