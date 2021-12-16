package dev.kibet.presentation.di

import dev.kibet.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CharactersViewModel(get(), get()) }
}
