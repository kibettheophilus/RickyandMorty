package dev.kibet.presentation.di

import dev.kibet.data.dataModule
import dev.kibet.domain.di.domainModule
import dev.kibet.presentation.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    module {
        listOf(domainModule, dataModule)
    }
    viewModel { CharactersViewModel(get()) }
}
