package com.example.notetakingapp.usecases

import com.example.notetakingapp.repositories.NoteRepository

class DeleteNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend fun execute(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}