package com.example.notetakingapp.repositories

import com.example.notetakingapp.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: Int): Note?
    suspend fun createNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNoteById(id: Int)
}