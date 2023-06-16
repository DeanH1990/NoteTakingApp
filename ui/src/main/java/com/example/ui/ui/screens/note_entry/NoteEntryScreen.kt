package com.example.ui.ui.screens.note_entry

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.example.ui.R
import com.example.ui.ui.model.NoteUiState
import com.example.ui.ui.utils.NoteTopAppBar
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun NoteEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean = true,
    viewModel: NoteEntryViewModel = getViewModel()
) {
    val coroutineScope = rememberCoroutineScope()

    BackHandler(
        onBack = {
            coroutineScope.launch {
                viewModel.saveNote()
                navigateBack()
            }
        })
    
    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(NoteEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = {
                    coroutineScope.launch {
                        viewModel.saveNote()
                        onNavigateUp()
                    }
                }
            )
        }
    ) { innerPadding ->
        NoteInputForm(
            noteUiState = viewModel.noteUiState,
            onValueChange = viewModel::updateUiState,
            modifier = modifier.padding(innerPadding))

    }
}

@Composable
fun NoteInputForm(
    noteUiState: NoteUiState,
    modifier: Modifier = Modifier,
    onValueChange: (NoteUiState) -> Unit = {}
) {

    val contentFocusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = noteUiState.title,
            onValueChange = { onValueChange(noteUiState.copy(title = it)) },
            label = { Text(stringResource(R.string.title_input_label)) },
            modifier = modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardActions = KeyboardActions(onNext = {
                contentFocusRequester.requestFocus()
            } ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true
        )
        TextField(
            value = noteUiState.content,
            onValueChange = { onValueChange(noteUiState.copy(content = it)) },
            label = { Text(stringResource(R.string.content_input_label)) },
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .focusRequester(contentFocusRequester),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
}