package com.example.notetakingapp.di

import androidx.room.Room
import com.example.data.dao.NoteDao
import com.example.data.database.NoteDatabase
import com.example.data.repository.NoteDataRepository
import com.example.notetakingapp.repositories.NoteRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    single { get<NoteDatabase>().noteDao() }

    single<NoteRepository> { NoteDataRepository(get()) }
}