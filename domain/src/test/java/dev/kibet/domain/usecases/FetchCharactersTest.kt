package dev.kibet.domain.usecases

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import dev.kibet.domain.models.Character
import dev.kibet.domain.repository.CharactersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

class FetchCharactersTest {

    // helper
    private var charactersRepository: CharactersRepository = mock()
    private val characterList = listOf<Character>()

    // SUT
    private val fetchCharacters = FetchCharacters(repository = charactersRepository)

    @Before
    fun setup() {
        charactersRepository.stub {
            onBlocking { this.getAllCharacters() }.doReturn(characterList)
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `FetchCharacters invoke should return a flow of character list`() = runBlocking {
        fetchCharacters.invoke().test {
            val list = awaitItem()
            awaitComplete()
            assertThat(characterList).isEqualTo(list)
        }
    }
}
