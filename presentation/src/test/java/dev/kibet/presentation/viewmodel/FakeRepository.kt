package dev.kibet.presentation.viewmodel

import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository

class FakeRepository : CharactersRepository {

    private val fakeCharacterDb = mutableListOf<Character>()

    override suspend fun getAllCharacters(): List<Character> = fakeCharacterDb

    override suspend fun getSingleCharacter(id: Int): Character = fakeCharacterDb.find {
        it.id == id
    } ?: Character(id = 1, name = "Default Character", image = "Default image")
}

