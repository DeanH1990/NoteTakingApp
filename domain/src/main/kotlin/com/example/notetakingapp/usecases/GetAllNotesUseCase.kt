package com.example.notetakingapp.usecases

import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {
    fun execute(): List<Note> {
        return noteRepository.getAllNotes()
    }
}