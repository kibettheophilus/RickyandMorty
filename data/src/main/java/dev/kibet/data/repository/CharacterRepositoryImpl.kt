package dev.kibet.data.repository

import dev.kibet.data.local.dao.CharactersDao
import dev.kibet.data.mappers.toDomain
import dev.kibet.data.mappers.toEntity
import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository

class CharacterRepositoryImpl(private val api: CharactersApi, private val dao: CharactersDao) :
    CharactersRepository {
    override suspend fun getAllCharacters(): List<Character> {
        val characters = dao.getCharacters()
        if (characters.size > 1) {
            return characters.map { it.toDomain() }
        } else {
            val characters = api.getAllCharacters()
            dao.saveCharacters(characters.results.map { it.toDomain().toEntity() })
            return characters.results.map { it.toDomain() }
        }
    }

    override suspend fun getSingleCharacter(id: Int): Character {
        return dao.getSingleCharacter(id).toDomain()
    }
}
