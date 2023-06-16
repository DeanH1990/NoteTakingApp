package com.example.data.repository

import com.example.data.dao.NoteDao
import com.example.data.mappers.DomainMapper
import com.example.data.mappers.EntityMapper
import com.example.data.utils.NoteValidation
import com.example.notetakingapp.models.Note
import com.example.notetakingapp.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDataRepository(private val noteDao: NoteDao): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        val allNotes = noteDao.getAllNotes()

        return allNotes.map { entityList ->
            entityList.map { EntityMapper.mapEntityNoteToDomain(it) } }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.let { EntityMapper.mapEntityNoteToDomain(it) }
    }

    override suspend fun createNote(note: Note) {
        NoteValidation.validateNote(note)
        val entity = DomainMapper.mapDomainNoteToEntity(note)
        noteDao.insertNote(entity)
    }

    override suspend fun updateNote(note: Note) {
        NoteValidation.validateNote(note)
        val entity = DomainMapper.mapDomainNoteToEntity(note)
        noteDao.updateNote(entity)
    }

    override suspend fun deleteNoteById(id: Int) {
        noteDao.deleteNoteById(id)
    }
}