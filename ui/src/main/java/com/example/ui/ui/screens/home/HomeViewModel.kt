package com.example.ui.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.usecases.GetAllNotesUseCase
import com.example.ui.ui.extensions.toNoteUiState
import com.example.ui.ui.model.NoteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllNotesUseCase: GetAllNotesUseCase): ViewModel() {

    private val _notes = MutableStateFlow<List<NoteUiState>>(listOf())
    val notes: StateFlow<List<NoteUiState>> = _notes

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {

            _notes.value = getAllNotesUseCase.execute().map { it.toNoteUiState() }
        }
    }
}