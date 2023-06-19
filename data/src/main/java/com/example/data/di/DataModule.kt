package com.example.data.di

import androidx.room.Room
import com.example.data.database.NoteDatabase
import com.example.data.repository.NoteRepositoryImpl
import com.example.notetakingapp.repositories.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val NOTE_DATABASE = "note_database"

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java,
            NOTE_DATABASE
        ).build()
    }

    single { get<NoteDatabase>().noteDao() }

    single<NoteRepository> { NoteRepositoryImpl(get()) }
}