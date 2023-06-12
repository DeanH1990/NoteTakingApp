package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class CreateNoteUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(note: Note) {
        noteRepository.createNote(note)
    }
}