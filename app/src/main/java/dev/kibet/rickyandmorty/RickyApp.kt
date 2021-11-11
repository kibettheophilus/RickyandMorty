package dev.kibet.rickyandmorty

import android.app.Application
import dev.kibet.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickyApp)
            modules(
                listOf(
                    presentationModule
                )
            )
        }
    }
}
