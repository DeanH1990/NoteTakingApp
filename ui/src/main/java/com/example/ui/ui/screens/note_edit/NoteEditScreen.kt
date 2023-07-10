package com.example.ui.ui.screens.note_edit

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import com.example.ui.R
import com.example.ui.ui.navigation.handleNavigation
import com.example.ui.ui.screens.note_entry.NoteInputForm
import com.example.ui.ui.utils.NoteTopAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun NoteEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    canDelete: Boolean = true,
    viewModel: NoteEditViewModel = getViewModel()
) {
    val context = LocalContext.current

    val deleteNoteEvent by viewModel.deleteNoteEvent.collectAsState()

    LaunchedEffect(key1 = deleteNoteEvent) {
        if (deleteNoteEvent) {
            navigateBack()
            viewModel.resetDeleteEvent()
        }
    }

    NoteEditContent(
        context,
        navigateBack,
        viewModel,
        canNavigateBack,
        canDelete,
        onNavigateUp,
        modifier
    )
}

@Composable
private fun NoteEditContent(
    context: Context,
    navigateBack: () -> Unit,
    viewModel: NoteEditViewModel,
    canNavigateBack: Boolean,
    canDelete: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier
    ) {
    BackHandler(
        onBack = {
            handleNavigation(
                context,
                R.string.erroneous_changes,
                navigateBack,
                viewModel,
                viewModel::isNoteEmpty,
                viewModel::updateNote
            )
        }
    )
    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(NoteEditDestination.titleRes),
                canNavigateBack = canNavigateBack,
                canDelete = canDelete,
                deleteNote = {
                    viewModel.viewModelScope.launch {
                    viewModel.deleteNote()
                    } },
                navigateUp = {
                    handleNavigation(
                        context,
                        R.string.erroneous_changes,
                        onNavigateUp,
                        viewModel,
                        viewModel::isNoteEmpty,
                        viewModel::updateNote
                    )
                }
            )
        }
    ) { innerPadding ->
        NoteInputForm(
            noteUiState = viewModel.noteUiState,
            onValueChange = viewModel::updateUiState,
            modifier = modifier.padding(innerPadding)
        )

    }
}