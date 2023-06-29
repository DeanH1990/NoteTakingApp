package com.example.ui.ui.screens.note_edit

import com.example.ui.R
import com.example.ui.ui.navigation.NavigationDestination

object NoteEditDestination: NavigationDestination {
    override val route = R.string.note_edit_route
    override val titleRes = R.string.note_edit_title
    const val noteIdArg = "noteId"
    val routeWithArgs = "$route/{$noteIdArg}"
}