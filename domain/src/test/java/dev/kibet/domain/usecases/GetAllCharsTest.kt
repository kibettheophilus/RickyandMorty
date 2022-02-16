package dev.kibet.domain.usecases

import dev.kibet.domain.repository.CharactersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

class GetAllCharsTest {

    val repo =  get<CharactersRepository::class.java>()

    @Test
    fun `get all characters returns a list of characters`() = runBlocking {
        val result = GetAllChars().invoke()

    }
}
