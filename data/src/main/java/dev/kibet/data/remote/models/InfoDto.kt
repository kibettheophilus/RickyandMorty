package dev.kibet.data.remote.models

data class InfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any?
)
