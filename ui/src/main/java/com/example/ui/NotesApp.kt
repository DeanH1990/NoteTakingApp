package com.example.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ui.ui.navigation.NotesNavGraph

@Composable
fun NotesApp(navController: NavHostController = rememberNavController()) {
    NotesNavGraph(navController = navController)
}