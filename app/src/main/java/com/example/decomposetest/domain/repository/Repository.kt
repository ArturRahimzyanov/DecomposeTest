package com.example.decomposetest.domain.repository

import com.example.decomposetest.domain.model.Data
import com.example.decomposetest.domain.model.GifsArray

interface Repository {

    var data: Data?

    suspend fun getGifs( limit: Int, offset: Int): GifsArray
}