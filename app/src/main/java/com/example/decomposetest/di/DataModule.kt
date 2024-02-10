package com.example.decomposetest.di

import com.example.decomposetest.data.Api
import com.example.decomposetest.model.Data
import com.example.decomposetest.model.ExampleJson2KtKotlin
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Response
import retrofit2.Retrofit

fun Json(): Json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
    classDiscriminator
}

fun provideApi(json: Json): Api {
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(Api::class.java)
}

val dataModule = module {
    factory<Repository> { RepositoryImpl(get()) }
    single { Json() }
    single { provideApi(get()) }
}

interface Repository {
    suspend fun getGifs(q: String): ArrayList<Data>
}

class RepositoryImpl(private val api: Api): Repository {
    override suspend fun getGifs(q: String): ArrayList<Data> {
        val apiKey = "WEWvCoe7W4MyEkPxJLmRxPdRNbJte63R"
        val query = "robot"
        val limit = 1
        val offset = 0
        return api.searchGifs(apiKey, query, limit, offset).body()!!.data
    }
}
