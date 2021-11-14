package dev.kibet.data

import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.data.repository.CharacterRepositoryImpl
import dev.kibet.domain.repository.CharactersRepository
import dev.kibet.domain.utils.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<CharactersRepository> { CharacterRepositoryImpl(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }
}
