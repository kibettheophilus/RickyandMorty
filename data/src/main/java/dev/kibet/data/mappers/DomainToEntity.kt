package dev.kibet.data.mappers

import dev.kibet.data.local.entity.CharacterEntity
import dev.kibet.domain.models.Character

fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id, name, image
)