package com.example.data.mappers

import com.example.data.entity.NoteEntity
import com.example.notetakingapp.models.Note

object DomainMapper {
    fun mapDomainNoteToEntity(note: Note): NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content
        )
    }
}