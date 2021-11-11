package dev.kibet.presentation.di

import dev.kibet.domain.di.domainModule
import org.koin.dsl.module

val presentationModule = module {
    listOf(
        domainModule
    )
}
