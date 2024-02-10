package com.example.decomposetest.data

import com.example.decomposetest.model.ExampleJson2KtKotlin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/v1/gifs/search")
    suspend fun searchGifs(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<ExampleJson2KtKotlin>

    companion object {
        const val BASE_URL = "https://api.giphy.com/"
        const val Api_Key = "api_key=WEWvCoe7W4MyEkPxJLmRxPdRNbJte63R"
        const val Bundle = "messaging_non_clips"
    }
}