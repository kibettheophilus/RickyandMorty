package dev.kibet.data.remote.api

import dev.kibet.data.remote.models.CharacterResponseDto
import retrofit2.http.GET

interface CharactersApi {

    @GET("character")
    suspend fun getAllCharacters(): CharacterResponseDto
}