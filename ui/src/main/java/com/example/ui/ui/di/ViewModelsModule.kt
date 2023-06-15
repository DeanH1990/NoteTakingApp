package com.example.ui.ui.di

import com.example.ui.ui.screens.home.HomeViewModel
import com.example.ui.ui.screens.note_entry.NoteEntryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { NoteEntryViewModel(get()) }
}