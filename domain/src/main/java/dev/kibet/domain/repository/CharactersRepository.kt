package dev.kibet.domain.repository

import dev.kibet.domain.models.Characters

interface CharactersRepository {
    suspend fun getAllCharacters(): List<Characters>
}