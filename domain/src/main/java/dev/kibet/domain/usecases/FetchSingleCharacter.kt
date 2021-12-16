package dev.kibet.domain.usecases

import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FetchSingleCharacter(private val repository: CharactersRepository) {
    suspend operator fun invoke(id: Int): Flow<Character> {
        return flowOf(repository.getSingleCharacter(id))
    }
}
