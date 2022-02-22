package dev.kibet.data.mappers

import com.google.common.truth.Truth.assertThat
import dev.kibet.data.local.entity.CharacterEntity
import dev.kibet.domain.models.Character
import org.junit.Test

class DomainToEntityKtTest {

    private val rickDomainCharacter = Character(1, "Rick", "Rick image")
    private val rickEntityCharacter = CharacterEntity(1, "Rick", "Rick image")

    @Test
    fun `toEntity converts a domain model to entity type`() {
        assertThat(rickDomainCharacter.toEntity()).isEqualTo(rickEntityCharacter)
    }
}
