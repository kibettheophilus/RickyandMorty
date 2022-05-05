package dev.kibet.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dev.kibet.data.local.db.CharactersDatabase
import dev.kibet.data.mappers.toEntity
import dev.kibet.domain.models.Character
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CharactersDaoTest {

    // SUT : subject under test
    private lateinit var charactersDao: CharactersDao

    // Helpers
    private lateinit var database: CharactersDatabase


//    @Before
//    fun setup() {
//        database = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            CharactersDatabase::class.java
//        ).allowMainThreadQueries().build()
//        charactersDao = database.charactersDao()
//    }

    /*@After
    fun tearDown() {
        database.close()
    }*/

  /*  @Test
    fun saveCharacters() = runBlocking {
        val characters = listOf(Character(11, "testOne", "")).map { it.toEntity() }
        charactersDao.saveCharacters(characters)
        val cacheResponse = charactersDao.getCharacters()
        assertThat(cacheResponse).isEqualTo(characters)
    }*/

   /* @Test
    fun getCharacters() = runBlocking {
        val characterList = listOf(Character(11, "testOne", "")).map { it.toEntity() }
        charactersDao.saveCharacters(characterList)
        assertThat(charactersDao.getCharacters()).isEqualTo(characterList)
    }*/

    /*@Test
    fun getSingleCharacter() = runBlocking {
        val characterList = listOf(
            Character(11, "testOne", ""),
            Character(2, "Victor Nyambura", "")
        ).map { it.toEntity() }
        charactersDao.saveCharacters(characterList)
        assertThat(charactersDao.getSingleCharacter(2)).isEqualTo(Character(2, "Victor Nyambura", "").toEntity())
    }*/
}
