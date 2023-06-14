package com.example.ui.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.models.Note
import com.example.notetakingapp.usecases.GetAllNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getAllNotesUseCase: GetAllNotesUseCase): ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(listOf())
    val notes: StateFlow<List<Note>> = _notes

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {

            _notes.value = getAllNotesUseCase.execute()
        }
    }
}