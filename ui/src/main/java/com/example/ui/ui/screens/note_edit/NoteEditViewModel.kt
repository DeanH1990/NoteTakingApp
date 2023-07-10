package com.example.ui.ui.screens.note_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.usecases.DeleteNoteByIdUseCase
import com.example.notetakingapp.usecases.GetNoteByIdUseCase
import com.example.notetakingapp.usecases.UpdateNoteUseCase
import com.example.ui.ui.extensions.isValid
import com.example.ui.ui.extensions.toNote
import com.example.ui.ui.extensions.toNoteUiState
import com.example.ui.ui.model.NoteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase
): ViewModel() {

    var noteUiState by mutableStateOf(NoteUiState())
        private set

    private val noteId: Int = checkNotNull(savedStateHandle[NoteEditDestination.noteIdArg])

    private val _deleteNoteEvent = MutableStateFlow(false)
    val deleteNoteEvent: StateFlow<Boolean> = _deleteNoteEvent

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

    fun updateUiState(newUiState: NoteUiState) {
        noteUiState = newUiState
    }

    suspend fun updateNote() {
        if (noteUiState.isValid()) {
            updateNoteUseCase(noteUiState.toNote())
        }
    }

    suspend fun deleteNote() {
        deleteNoteByIdUseCase(noteId)
        _deleteNoteEvent.value = true
    }

    fun resetDeleteEvent() {
        _deleteNoteEvent.value = false
    }

    fun isNoteEmpty(): Boolean {
        return noteUiState.title.isEmpty() || noteUiState.content.isEmpty()
    }
}