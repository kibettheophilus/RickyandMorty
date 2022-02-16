package dev.kibet.data.repository

import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository

class FakeRepositoryImpl : CharactersRepository {
    override suspend fun getAllCharacters(): List<Character> {
        return listOf(
            Character(1, "One", ""),
            Character(2, "", ""),
            Character(3, "", "")
        )
    }

    override suspend fun getSingleCharacter(id: Int): Character {
        return Character(1, "", "")
    }
}
