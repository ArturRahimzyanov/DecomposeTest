package com.example.decomposetest.di

import com.example.decomposetest.data.Api
import com.example.decomposetest.data.repositoryImpl.RepositoryImpl
import com.example.decomposetest.domain.repository.Repository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

fun provideApi(json: Json): Api {
    return Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(Api::class.java)
}

val dataModule = module {
    single <Repository> { RepositoryImpl(get()) }
    single {
        Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            classDiscriminator
        }
    }
    single { provideApi(json = get()) }
}

