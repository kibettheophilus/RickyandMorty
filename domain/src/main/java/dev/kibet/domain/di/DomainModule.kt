package dev.kibet.domain.di

import dev.kibet.domain.usecases.FetchCharacters
import org.koin.dsl.module

val domainModule = module {
    single { FetchCharacters(get()) }
}
