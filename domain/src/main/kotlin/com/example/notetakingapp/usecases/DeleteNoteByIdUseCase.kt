package com.example.notetakingapp.usecases

import com.example.notetakingapp.repositories.NoteRepository

class DeleteNoteByIdUseCase(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(id: Int) {
        noteRepository.deleteNoteById(id)
    }
}