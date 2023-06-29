package com.example.ui.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.ui.R
import com.example.ui.ui.model.NoteUiState
import com.example.ui.ui.theme.*
import com.example.ui.ui.utils.NoteTopAppBar
import org.koin.androidx.compose.getViewModel

private const val GRID_CELLS = 2
private const val MAX_TITLE_LENGTH = 14
private const val MAX_LINES_LONG_TITLE = 6
private const val MAX_LINES_SHORT_TITLE = 7

@Composable
fun HomeScreen(
    navigateToNoteEntry: () -> Unit,
    navigateToNoteEdit: (Int) -> Unit,
    canNavigateBack: Boolean = false,
    canDelete: Boolean = false,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = getViewModel()
) {
    val allNotesVm = viewModel.notes.collectAsState().value

    HomeScreenContent(
        navigateToNoteEntry,
        navigateToNoteEdit,
        canNavigateBack,
        canDelete,
        modifier,
        allNotesVm
    )
}

@Composable
private fun HomeScreenContent(
    navigateToNoteEntry: () -> Unit,
    navigateToNoteEdit: (Int) -> Unit,
    canNavigateBack: Boolean,
    canDelete: Boolean,
    modifier: Modifier,
    allNotesVm: List<NoteUiState>
) {
    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = canNavigateBack,
                canDelete = canDelete
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNoteEntry,
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_note_button),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    ) { innerPadding ->

        HomeBody(
            noteList = allNotesVm,
            navigateToNoteEdit = navigateToNoteEdit,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    noteList: List<NoteUiState>,
    navigateToNoteEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(BODY_PADDING),
        verticalArrangement = Arrangement.spacedBy(LIST_SPACING)
    ) {
        if (noteList.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.subtitle1
            )
        } else {
            NoteList(noteList = noteList, navigateToNoteEdit = navigateToNoteEdit)
        }
    }
}
@Composable
private fun NoteList(
    noteList: List<NoteUiState>,
    navigateToNoteEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_CELLS),
        contentPadding = PaddingValues(GRID_PADDING),
        content = {
            items(noteList) { note ->
                NoteCard(note = note, navigateToNoteEdit = navigateToNoteEdit, modifier = modifier)
            }
        }
    )
}

@Composable
private fun NoteCard(
    note: NoteUiState,
    navigateToNoteEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(CARD_CORNER_RADIUS),
        elevation = CARD_ELEVATION,
        modifier = modifier
            .clickable { navigateToNoteEdit(note.id) }
            .padding(CARD_PADDING)
            .fillMaxWidth()
            .heightIn(min = CARD_MIN_HEIGHT, max = CARD_MAX_HEIGHT)
    ) {
        Column(
            modifier = modifier.padding(CARD_PADDING)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = modifier.height(TEXT_SPACING))
            Text(
                text = note.truncatedContent,
                style = MaterialTheme.typography.body1,
                maxLines = if (note.title.length > MAX_TITLE_LENGTH) MAX_LINES_LONG_TITLE else MAX_LINES_SHORT_TITLE,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    NoteTakingAppTheme {
        HomeScreen(navigateToNoteEntry = {}, navigateToNoteEdit = {})
    }
}