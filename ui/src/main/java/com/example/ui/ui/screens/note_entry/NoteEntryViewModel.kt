package com.example.ui.ui.screens.note_entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notetakingapp.usecases.CreateNoteUseCase
import com.example.ui.ui.extensions.isValid
import com.example.ui.ui.extensions.toNote
import com.example.ui.ui.model.NoteUiState

class NoteEntryViewModel(private val createNoteUseCase: CreateNoteUseCase): ViewModel() {

    var noteUiState by mutableStateOf(NoteUiState())
    private set

    fun updateUiState(newUiState: NoteUiState) {
        noteUiState = newUiState
    }

    suspend fun saveNote() {
        if (noteUiState.isValid()) {
            createNoteUseCase(noteUiState.toNote())
        }
    }
    fun isNoteEmpty(): Boolean {
        return noteUiState.title.isEmpty() || noteUiState.content.isEmpty()
    }
}