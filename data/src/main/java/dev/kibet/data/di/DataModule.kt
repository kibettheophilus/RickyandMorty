package dev.kibet.data

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dev.kibet.data.local.db.CharactersDatabase
import dev.kibet.data.remote.api.CharactersApi
import dev.kibet.data.repository.CharacterRepositoryImpl
import dev.kibet.domain.repository.CharactersRepository
import dev.kibet.domain.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.dsl.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<CharactersRepository> { CharacterRepositoryImpl(get(), get()) }
    single { provideRetrofit() }
    single { provideOkhttp(get()) }
    single {
        Room.databaseBuilder(androidApplication(), CharactersDatabase::class.java, "charaters.db")
            .fallbackToDestructiveMigration().build()
    }
    single { get<CharactersDatabase>().charactersDao() }
}

fun provideRetrofit(): CharactersApi {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CharactersApi::class.java)
}

fun provideOkhttp(context: Context): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(true)
                .build()
        )
    return builder.build()
}
