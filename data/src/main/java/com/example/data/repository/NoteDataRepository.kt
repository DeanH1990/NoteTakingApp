package com.example.data.repository

import com.example.data.dao.NoteDao
import com.example.data.mappers.EntityMapper
import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository

class NoteDataRepository(private val noteDao: NoteDao): NoteRepository {
    override suspend fun getAllNotes(): List<Note> {
        val allNotes = noteDao.getAllNotes()

        return allNotes.map { EntityMapper.mapEntityNoteToDomain(it) }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.let { EntityMapper.mapEntityNoteToDomain(it) }
    }


}