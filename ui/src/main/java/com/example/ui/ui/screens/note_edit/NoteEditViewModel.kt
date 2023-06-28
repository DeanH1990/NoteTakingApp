package com.example.ui.ui.screens.note_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.usecases.GetNoteByIdUseCase
import com.example.notetakingapp.usecases.UpdateNoteUseCase
import com.example.ui.ui.extensions.toNoteUiState
import com.example.ui.ui.model.NoteUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
): ViewModel() {

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    private val noteId: Int = checkNotNull(savedStateHandle[NoteEditDestination.noteIdArg])

    init {
        getNoteById()
    }

    private fun getNoteById() {
        viewModelScope.launch {
            getNoteByIdUseCase(noteId).collect { note ->
                note?.let {
                    noteUiState = it.toNoteUiState()
                }
            }
        }
    }
}