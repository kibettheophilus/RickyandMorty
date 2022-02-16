package dev.kibet.data.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import dev.kibet.data.local.dao.CharactersDao
import dev.kibet.data.local.db.CharactersDatabase
import dev.kibet.data.mappers.toEntity
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

    // helpers
    private lateinit var apiService: CharactersApi
    private lateinit var dataBase: CharactersDatabase
    private lateinit var charactersDao: CharactersDao

    // system under test
    private lateinit var repositoryImpl: CharacterRepositoryImpl

    // utilities
    private val character = Character(1, "Test Character", "image")
    private val character2 = Character(2, "Test Character 2", "image")
    private val characterDto = ResultDto(
        "",
        listOf(),
        "",
        1,
        "",
        LocationDto("", ""),
        "",
        OriginDto("", ""),
        "",
        "",
        "",
        ""
    )
    private val responseDto = CharacterResponseDto(InfoDto(1, "", 2, 4f), listOf(characterDto))
    private val characters = listOf(character)

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
        val results = listOf(character, character2).map { it.toEntity() }
        charactersDao.saveCharacters(results)
        assertThat(repositoryImpl.getAllCharacters().size).isEqualTo(2)
    }
}
