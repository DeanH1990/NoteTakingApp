package com.example.ui.ui.screens.home

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
import androidx.compose.ui.unit.dp
import com.example.ui.R
import com.example.ui.ui.model.NoteUiState
import com.example.ui.ui.theme.NoteTakingAppTheme
import com.example.ui.ui.utils.NoteTopAppBar
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navigateToNoteEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = getViewModel()
) {
    val allNotesVm = viewModel.notes.collectAsState().value

    Scaffold(
        topBar = {
            NoteTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNoteEntry ,
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
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun HomeBody(
    noteList: List<NoteUiState>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (noteList.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.subtitle1
            )
        } else {
            NoteList(noteList = noteList)
        }
    }
}
@Composable
private fun NoteList(
    noteList: List<NoteUiState>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        content = {
            items(noteList) { note ->
                NoteCard(note = note, modifier = modifier)
            }
        }
    )
}

@Composable
private fun NoteCard(
    note: NoteUiState,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .heightIn(min = 190.dp, max = 190.dp)
    ) {
        Column(
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = note.truncatedContent,
                style = MaterialTheme.typography.body1,
                maxLines = if (note.title.length > 14) 6 else 7,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    NoteTakingAppTheme {
        HomeScreen(navigateToNoteEntry = {})
    }
}