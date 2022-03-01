package dev.kibet.presentation.viewmodel

import dev.kibet.domain.usecases.FetchCharacters
import dev.kibet.domain.usecases.FetchSingleCharacter
import dev.kibet.presentation.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class CharactersViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val fetchCharacters = FetchCharacters(repository = FakeRepository())
    private val fetchSingleCharacter = FetchSingleCharacter(repository = FakeRepository())
    private val charactersVm = CharactersViewModel(
        fetchCharacters, fetchSingleCharacter
    )

    @Test
    fun `get characters should post UiState to fetchCharacterStatus`() = runBlocking {
    }
}
