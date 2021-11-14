package dev.kibet.data.repository

import dev.kibet.data.mappers.toDomain
import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.domain.models.Characters
import dev.kibet.domain.repository.CharactersRepository

class CharacterRepositoryImpl(private val api: CharactersApi) : CharactersRepository {
    override suspend fun getAllCharacters(): List<Characters> {
        return api.getAllChacters().results.map { it.toDomain() }
    }
}
