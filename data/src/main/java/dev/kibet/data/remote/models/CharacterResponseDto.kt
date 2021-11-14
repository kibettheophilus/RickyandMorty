package dev.kibet.data.remote.models

data class CharacterResponseDto(
    val info: InfoDto,
    val results: List<ResultDto>
)