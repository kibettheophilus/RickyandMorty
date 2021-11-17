package dev.kibet.data.mappers

import dev.kibet.data.remote.models.ResultDto
import dev.kibet.domain.models.Character

fun ResultDto.toDomain(): Character {
    return Character(
        name = name,
        image = image,
        id = id
    )
}
