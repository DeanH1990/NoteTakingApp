package com.example.ui.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetakingapp.models.Note
import com.example.ui.R
import com.example.ui.ui.theme.NoteTakingAppTheme
import com.example.ui.ui.utils.NoteTopAppBar
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
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
    noteList: List<Note>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (noteList.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_list),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeScreen() {
    NoteTakingAppTheme {
        HomeScreen()
    }
}