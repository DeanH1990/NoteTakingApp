package com.example.ui.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.ui.ui.screens.home.HomeDestination
import com.example.ui.ui.screens.home.HomeScreen


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
            HomeScreen()
        }
    }
}