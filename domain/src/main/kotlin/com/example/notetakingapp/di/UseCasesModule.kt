package com.example.notetakingapp.di

import com.example.notetakingapp.usecases.GetAllNotesUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetAllNotesUseCase(get()) }
}