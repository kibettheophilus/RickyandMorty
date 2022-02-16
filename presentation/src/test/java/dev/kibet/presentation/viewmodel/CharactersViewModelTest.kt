package dev.kibet.presentation.viewmodel

import dev.kibet.data.repository.FakeRepositoryImpl
import dev.kibet.domain.usecases.FetchCharacters
import dev.kibet.domain.usecases.FetchSingleCharacter
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CharactersViewModelTest {

    private lateinit var charactersVm: CharactersViewModel

    @Before
    fun setUp() {
        charactersVm = CharactersViewModel(
            FetchCharacters(FakeRepositoryImpl()),
            FetchSingleCharacter(FakeRepositoryImpl())
        )
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getFetchCharacters() {
        runBlocking {
            charactersVm.getCharacters()
        }
    }

    @Test
    fun getFetchSingleCharacterStatus() {
    }

    @Test
    fun getSingleCharacter() {
    }
}
