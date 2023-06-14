package com.example.notetakingapp

import android.app.Application
import com.example.data.di.dataModule
import com.example.notetakingapp.di.useCasesModule
import com.example.ui.ui.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class NoteApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NoteApplication)
            modules(listOf(dataModule, useCasesModule, viewModelsModule))
        }
    }
}