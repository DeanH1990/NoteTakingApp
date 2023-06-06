package com.example.notetakingapp.repositories

import com.example.notetakingapp.models.Note

interface NoteRepository {
    fun getAllNotes(): List<Note>
    fun getNoteById(id: Int): Note
    fun createNote(note: Note)
    fun updateNote(note: Note)
    fun deleteNoteById(id: Int)
}