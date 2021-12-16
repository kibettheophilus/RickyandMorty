package dev.kibet.domain.repository

import dev.kibet.domain.models.Character

interface CharactersRepository {
    suspend fun getAllCharacters(): List<Character>
    suspend fun getSingleCharacter(id: Int): Character
}