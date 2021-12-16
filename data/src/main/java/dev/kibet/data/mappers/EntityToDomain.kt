package dev.kibet.data.mappers

import dev.kibet.data.local.entity.CharacterEntity
import dev.kibet.domain.models.Character

fun CharacterEntity.toDomain(): Character {
    return Character(
        id, name, image
    )
}