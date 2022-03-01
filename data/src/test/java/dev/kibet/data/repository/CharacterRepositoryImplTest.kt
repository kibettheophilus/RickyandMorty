package dev.kibet.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import dev.kibet.data.local.dao.CharactersDao
import dev.kibet.data.local.db.CharactersDatabase
import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.data.remote.models.* // ktlint-disable no-wildcard-imports
import dev.kibet.domain.models.Character
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CharacterRepositoryImplTest {

    // system under test
    private lateinit var repositoryImpl: CharacterRepositoryImpl

    // helpers
    private lateinit var apiService: CharactersApi
    private lateinit var dataBase: CharactersDatabase
    private lateinit var charactersDao: CharactersDao

    // utilities
    private val character = Character(1, "Test Character", "image")
    private val character2 = Character(2, "Test Character2", "image")
    private val characterDto = ResultDto(
        "",
        listOf(),
        "",
        1,
        "image",
        LocationDto("", ""),
        "Test Character",
        OriginDto("", ""),
        "",
        "",
        "",
        ""
    )
    private val characterDto2 = ResultDto(
        "",
        listOf(),
        "",
        2,
        "image",
        LocationDto("", ""),
        "Test Character2",
        OriginDto("", ""),
        "",
        "",
        "",
        ""
    )
    private val responseDto = CharacterResponseDto(InfoDto(1, "", 2, 4f), listOf(characterDto, characterDto2))
    private val characters = listOf(character, character2)

    @Before
    fun setup() {
        apiService = mock()
        apiService.stub {
            onBlocking { this.getAllCharacters() }.doReturn(responseDto)
        }

        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharactersDatabase::class.java
        ).allowMainThreadQueries().build()
        charactersDao = dataBase.charactersDao()
        repositoryImpl = CharacterRepositoryImpl(apiService, charactersDao)
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun getAllCharacters() = runBlocking {
        assertThat(repositoryImpl.getAllCharacters()).isEqualTo(
            characters
        )
    }
}
