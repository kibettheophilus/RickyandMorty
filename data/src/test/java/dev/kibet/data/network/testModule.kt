package dev.kibet.data.network

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import dev.kibet.data.local.dao.CharactersDao
import dev.kibet.data.local.db.CharactersDatabase
import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.data.remote.models.CharacterResponseDto
import dev.kibet.data.remote.models.InfoDto
import dev.kibet.data.remote.models.LocationDto
import dev.kibet.data.remote.models.OriginDto
import dev.kibet.data.remote.models.ResultDto
import dev.kibet.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersApiTestUsingMockWebServer {

    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val charactersApiService by lazy {
        retrofit.create(CharactersApi::class.java)
    }

    private val testJson = """{ "info": {
        "count": 826,
        "pages": 42,
        "next": "https://rickandmortyapi.com/api/character?page=2",
        "prev": null
    },
    "results": [
    {
            "id": 1,
            "name": "Rick Sanchez",
            "status": "Alive",
            "species": "Human",
            "type": "",
            "gender": "Male",
            "origin": {
                "name": "Earth (C-137)",
                "url": "https://rickandmortyapi.com/api/location/1"
            },
            "location": {
                "name": "Citadel of Ricks",
                "url": "https://rickandmortyapi.com/api/location/3"
            },
            "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "episode": [
                "https://rickandmortyapi.com/api/episode/1",
                "https://rickandmortyapi.com/api/episode/2",
                "https://rickandmortyapi.com/api/episode/3",
                "https://rickandmortyapi.com/api/episode/4",
                "https://rickandmortyapi.com/api/episode/5",
                "https://rickandmortyapi.com/api/episode/6",
                "https://rickandmortyapi.com/api/episode/7",
                "https://rickandmortyapi.com/api/episode/8",
                "https://rickandmortyapi.com/api/episode/9",
                "https://rickandmortyapi.com/api/episode/10",
                "https://rickandmortyapi.com/api/episode/11",
                "https://rickandmortyapi.com/api/episode/12",
                "https://rickandmortyapi.com/api/episode/13",
                "https://rickandmortyapi.com/api/episode/14",
                "https://rickandmortyapi.com/api/episode/15",
                "https://rickandmortyapi.com/api/episode/16",
                "https://rickandmortyapi.com/api/episode/17",
                "https://rickandmortyapi.com/api/episode/18",
                "https://rickandmortyapi.com/api/episode/19",
                "https://rickandmortyapi.com/api/episode/20",
                "https://rickandmortyapi.com/api/episode/21",
                "https://rickandmortyapi.com/api/episode/22",
                "https://rickandmortyapi.com/api/episode/23",
                "https://rickandmortyapi.com/api/episode/24",
                "https://rickandmortyapi.com/api/episode/25",
                "https://rickandmortyapi.com/api/episode/26",
                "https://rickandmortyapi.com/api/episode/27",
                "https://rickandmortyapi.com/api/episode/28",
                "https://rickandmortyapi.com/api/episode/29",
                "https://rickandmortyapi.com/api/episode/30",
                "https://rickandmortyapi.com/api/episode/31",
                "https://rickandmortyapi.com/api/episode/32",
                "https://rickandmortyapi.com/api/episode/33",
                "https://rickandmortyapi.com/api/episode/34",
                "https://rickandmortyapi.com/api/episode/35",
                "https://rickandmortyapi.com/api/episode/36",
                "https://rickandmortyapi.com/api/episode/37",
                "https://rickandmortyapi.com/api/episode/38",
                "https://rickandmortyapi.com/api/episode/39",
                "https://rickandmortyapi.com/api/episode/40",
                "https://rickandmortyapi.com/api/episode/41",
                "https://rickandmortyapi.com/api/episode/42",
                "https://rickandmortyapi.com/api/episode/43",
                "https://rickandmortyapi.com/api/episode/44",
                "https://rickandmortyapi.com/api/episode/45",
                "https://rickandmortyapi.com/api/episode/46",
                "https://rickandmortyapi.com/api/episode/47",
                "https://rickandmortyapi.com/api/episode/48",
                "https://rickandmortyapi.com/api/episode/49",
                "https://rickandmortyapi.com/api/episode/50",
                "https://rickandmortyapi.com/api/episode/51"
            ],
            "url": "https://rickandmortyapi.com/api/character/1",
            "created": "2017-11-04T18:48:46.250Z"
        }
    ]
    }"""

    private val expectedResponse = CharacterResponseDto(
        info = InfoDto(
            count = 826,
            pages = 42,
            next = "https://rickandmortyapi.com/api/character?page=2",
            prev = null
        ),
        results = listOf(
            ResultDto(
                created = "2017-11-04T18:48:46.250Z",
                episode = listOf(
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2",
                    "https://rickandmortyapi.com/api/episode/3",
                    "https://rickandmortyapi.com/api/episode/4",
                    "https://rickandmortyapi.com/api/episode/5",
                    "https://rickandmortyapi.com/api/episode/6",
                    "https://rickandmortyapi.com/api/episode/7",
                    "https://rickandmortyapi.com/api/episode/8",
                    "https://rickandmortyapi.com/api/episode/9",
                    "https://rickandmortyapi.com/api/episode/10",
                    "https://rickandmortyapi.com/api/episode/11",
                    "https://rickandmortyapi.com/api/episode/12",
                    "https://rickandmortyapi.com/api/episode/13",
                    "https://rickandmortyapi.com/api/episode/14",
                    "https://rickandmortyapi.com/api/episode/15",
                    "https://rickandmortyapi.com/api/episode/16",
                    "https://rickandmortyapi.com/api/episode/17",
                    "https://rickandmortyapi.com/api/episode/18",
                    "https://rickandmortyapi.com/api/episode/19",
                    "https://rickandmortyapi.com/api/episode/20",
                    "https://rickandmortyapi.com/api/episode/21",
                    "https://rickandmortyapi.com/api/episode/22",
                    "https://rickandmortyapi.com/api/episode/23",
                    "https://rickandmortyapi.com/api/episode/24",
                    "https://rickandmortyapi.com/api/episode/25",
                    "https://rickandmortyapi.com/api/episode/26",
                    "https://rickandmortyapi.com/api/episode/27",
                    "https://rickandmortyapi.com/api/episode/28",
                    "https://rickandmortyapi.com/api/episode/29",
                    "https://rickandmortyapi.com/api/episode/30",
                    "https://rickandmortyapi.com/api/episode/31",
                    "https://rickandmortyapi.com/api/episode/32",
                    "https://rickandmortyapi.com/api/episode/33",
                    "https://rickandmortyapi.com/api/episode/34",
                    "https://rickandmortyapi.com/api/episode/35",
                    "https://rickandmortyapi.com/api/episode/36",
                    "https://rickandmortyapi.com/api/episode/37",
                    "https://rickandmortyapi.com/api/episode/38",
                    "https://rickandmortyapi.com/api/episode/39",
                    "https://rickandmortyapi.com/api/episode/40",
                    "https://rickandmortyapi.com/api/episode/41",
                    "https://rickandmortyapi.com/api/episode/42",
                    "https://rickandmortyapi.com/api/episode/43",
                    "https://rickandmortyapi.com/api/episode/44",
                    "https://rickandmortyapi.com/api/episode/45",
                    "https://rickandmortyapi.com/api/episode/46",
                    "https://rickandmortyapi.com/api/episode/47",
                    "https://rickandmortyapi.com/api/episode/48",
                    "https://rickandmortyapi.com/api/episode/49",
                    "https://rickandmortyapi.com/api/episode/50",
                    "https://rickandmortyapi.com/api/episode/51"
                ),
                gender = "Male",
                id = 1,
                url = "https://rickandmortyapi.com/api/character/1",
                location = LocationDto(
                    name = "Citadel of Ricks",
                    url = "https://rickandmortyapi.com/api/location/3"
                ),
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                name = "Rick Sanchez",
                origin = OriginDto(
                    name = "Earth (C-137)",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                species = "Human",
                status = "Alive", type = ""

            )
        )
    )

    @Test
    fun `getAllCharacters returns a character response dto`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(testJson)
                .setResponseCode(200)
        )
        val test = charactersApiService.getAllCharacters()
        assertThat(test).isEqualTo(expectedResponse)
        assertThat("/character").isEqualTo(mockWebServer.takeRequest().path)
    }
}

@RunWith(RobolectricTestRunner::class)
class CharactersApiTestUsingMockito {

    // region: Initilization

    // SUT: Subject under test
    private lateinit var charactersApi: CharactersApi
    private lateinit var charactersDao: CharactersDao
    private lateinit var database: CharactersDatabase
    // endregion

    // region: Helpers && Utilities
    private lateinit var repository: CharacterRepositoryImpl
    private val expectedResponse = CharacterResponseDto(
        info = InfoDto(
            count = 826,
            pages = 42,
            next = "https://rickandmortyapi.com/api/character?page=2",
            prev = null
        ),
        results = listOf(
            ResultDto(
                created = "2017-11-04T18:48:46.250Z",
                episode = listOf(
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2",
                    "https://rickandmortyapi.com/api/episode/3",
                    "https://rickandmortyapi.com/api/episode/4",
                    "https://rickandmortyapi.com/api/episode/5",
                    "https://rickandmortyapi.com/api/episode/6",
                    "https://rickandmortyapi.com/api/episode/7",
                    "https://rickandmortyapi.com/api/episode/8",
                    "https://rickandmortyapi.com/api/episode/9",
                    "https://rickandmortyapi.com/api/episode/10",
                    "https://rickandmortyapi.com/api/episode/11",
                    "https://rickandmortyapi.com/api/episode/12",
                    "https://rickandmortyapi.com/api/episode/13",
                    "https://rickandmortyapi.com/api/episode/14",
                    "https://rickandmortyapi.com/api/episode/15",
                    "https://rickandmortyapi.com/api/episode/16",
                    "https://rickandmortyapi.com/api/episode/17",
                    "https://rickandmortyapi.com/api/episode/18",
                    "https://rickandmortyapi.com/api/episode/19",
                    "https://rickandmortyapi.com/api/episode/20",
                    "https://rickandmortyapi.com/api/episode/21",
                    "https://rickandmortyapi.com/api/episode/22",
                    "https://rickandmortyapi.com/api/episode/23",
                    "https://rickandmortyapi.com/api/episode/24",
                    "https://rickandmortyapi.com/api/episode/25",
                    "https://rickandmortyapi.com/api/episode/26",
                    "https://rickandmortyapi.com/api/episode/27",
                    "https://rickandmortyapi.com/api/episode/28",
                    "https://rickandmortyapi.com/api/episode/29",
                    "https://rickandmortyapi.com/api/episode/30",
                    "https://rickandmortyapi.com/api/episode/31",
                    "https://rickandmortyapi.com/api/episode/32",
                    "https://rickandmortyapi.com/api/episode/33",
                    "https://rickandmortyapi.com/api/episode/34",
                    "https://rickandmortyapi.com/api/episode/35",
                    "https://rickandmortyapi.com/api/episode/36",
                    "https://rickandmortyapi.com/api/episode/37",
                    "https://rickandmortyapi.com/api/episode/38",
                    "https://rickandmortyapi.com/api/episode/39",
                    "https://rickandmortyapi.com/api/episode/40",
                    "https://rickandmortyapi.com/api/episode/41",
                    "https://rickandmortyapi.com/api/episode/42",
                    "https://rickandmortyapi.com/api/episode/43",
                    "https://rickandmortyapi.com/api/episode/44",
                    "https://rickandmortyapi.com/api/episode/45",
                    "https://rickandmortyapi.com/api/episode/46",
                    "https://rickandmortyapi.com/api/episode/47",
                    "https://rickandmortyapi.com/api/episode/48",
                    "https://rickandmortyapi.com/api/episode/49",
                    "https://rickandmortyapi.com/api/episode/50",
                    "https://rickandmortyapi.com/api/episode/51"
                ),
                gender = "Male",
                id = 1,
                url = "https://rickandmortyapi.com/api/character/1",
                location = LocationDto(
                    name = "Citadel of Ricks",
                    url = "https://rickandmortyapi.com/api/location/3"
                ),
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                name = "Rick Sanchez",
                origin = OriginDto(
                    name = "Earth (C-137)",
                    url = "https://rickandmortyapi.com/api/location/1"
                ),
                species = "Human",
                status = "Alive", type = ""

            )
        )
    )

    // endregion

    // region: Before && After
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharactersDatabase::class.java
        ).allowMainThreadQueries().build()
        charactersDao = database.charactersDao()

        charactersApi = mock()

        repository = CharacterRepositoryImpl(charactersApi, charactersDao)
    }

    @After
    fun tearDown() {
        database.close()
    }
    // endregion

    // region:getAllCharacters Test
    @Test
    fun `getAllCharacters returns character dto`() = runBlocking {
        whenever(charactersApi.getAllCharacters()).thenReturn(expectedResponse)

        assertThat(repository.getAllCharacters().size).isEqualTo(expectedResponse.results.size)
    }
    // endregion
}
