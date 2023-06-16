package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }
}