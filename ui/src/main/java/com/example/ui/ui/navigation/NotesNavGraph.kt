package com.example.ui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.ui.ui.screens.home.HomeDestination
import com.example.ui.ui.screens.home.HomeScreen
import com.example.ui.ui.screens.note_edit.NoteEditDestination
import com.example.ui.ui.screens.note_edit.NoteEditScreen
import com.example.ui.ui.screens.note_entry.NoteEntryDestination
import com.example.ui.ui.screens.note_entry.NoteEntryScreen


@Composable
fun NotesNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = context.getString(HomeDestination.route),
        modifier = modifier
    ) {
        composable(route = context.getString(HomeDestination.route)) {
            HomeScreen(
                navigateToNoteEntry = {
                    navController.navigate(context.getString(NoteEntryDestination.route))
                },
                navigateToNoteEdit = {
                    navController.navigate("${NoteEditDestination.route}/${it}")
                }
            )
        }
        composable(route = context.getString(NoteEntryDestination.route)) {
            NoteEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(route = NoteEditDestination.routeWithArgs,
            arguments = listOf(navArgument(NoteEditDestination.noteIdArg) {
                type = NavType.IntType
            })
        ) {
            NoteEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}