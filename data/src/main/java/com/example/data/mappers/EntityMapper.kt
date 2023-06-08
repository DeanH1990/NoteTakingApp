package com.example.data.mappers

import com.example.data.entity.NoteEntity
import com.example.notetakingapp.models.Note
import com.example.notetakingapp.models.TextNote

object EntityMapper {
    fun mapEntityNoteToDomain(entity: NoteEntity): Note {
        return TextNote(
            id = entity.id,
            title = entity.title,
            content = entity.content
        )
    }
}