package com.example.decomposetest.data.repositoryImpl

import com.example.decomposetest.data.Api
import com.example.decomposetest.domain.model.Data
import com.example.decomposetest.domain.model.GifsArray
import com.example.decomposetest.domain.repository.Repository

class RepositoryImpl(private val api: Api): Repository {

    override var data: Data? = null

    override suspend fun getGifs(limit: Int, offset: Int, q: String): GifsArray {
        return api.searchGifs(API_KEY, q, limit, offset).body()!!
    }

    companion object {
        const val API_KEY = "WEWvCoe7W4MyEkPxJLmRxPdRNbJte63R"
    }
}