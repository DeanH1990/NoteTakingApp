package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int): Flow<Note?> {
        return noteRepository.getNoteById(id)
    }
}