package com.example.notetakingapp.di

import com.example.notetakingapp.usecases.CreateNoteUseCase
import com.example.notetakingapp.usecases.GetAllNotesUseCase
import com.example.notetakingapp.usecases.GetNoteByIdUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetAllNotesUseCase(get()) }
    factory { CreateNoteUseCase(get()) }
    factory { GetNoteByIdUseCase(get()) }
}