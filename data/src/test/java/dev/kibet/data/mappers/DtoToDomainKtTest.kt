package dev.kibet.data.mappers

import com.google.common.truth.Truth.assertThat
import dev.kibet.data.remote.models.LocationDto
import dev.kibet.data.remote.models.OriginDto
import dev.kibet.data.remote.models.ResultDto
import dev.kibet.domain.models.Character
import org.junit.Test

class DtoToDomainKtTest {

    private val mortyDto = ResultDto(
        "",
        listOf(),
        "",
        1,
        "",
        LocationDto("", ""),
        "Morty",
        OriginDto("", ""),
        "",
        "",
        "",
        ""
    )
    private val mortyDomain = Character(id = 1, "Morty", "")

    @Test
    fun `toDomain returns a domain model from a dto`() {
        assertThat(mortyDto.toDomain()).isEqualTo(mortyDomain)
    }
}
