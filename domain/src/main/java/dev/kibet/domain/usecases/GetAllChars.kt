package dev.kibet.domain.usecases

import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository

class GetAllChars(private val repository: CharactersRepository) {
    suspend operator fun invoke(): List<Character> {
        return listOf()
    }
}
