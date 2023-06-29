package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        noteRepository.updateNote(note)
    }
}