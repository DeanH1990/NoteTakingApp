package com.example.notetakingapp.models

data class TextNote(
    override val id: Int,
    override val title: String,
    override val content: String
) : Note()
