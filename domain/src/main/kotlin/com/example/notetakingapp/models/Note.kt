package com.example.notetakingapp.models

sealed class Note {
    abstract val id: Int
    abstract val title: String
    abstract val content: String
}