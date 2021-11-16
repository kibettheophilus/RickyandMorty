package dev.kibet.data.mappers

import dev.kibet.data.remote.models.ResultDto
import dev.kibet.domain.models.Characters

fun ResultDto.toDomain(): Characters {
    return Characters(
        name = name,
        image = image,
        id = id
    )
}