package dev.kibet.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetAllCharsTest {

    @Test
    fun `get all characters returns a list of characters`() = runBlocking {
        val result = GetAllChars().invoke()
    }
}
