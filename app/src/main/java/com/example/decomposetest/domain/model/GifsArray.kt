package com.example.decomposetest.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifsArray (
    @SerialName("data") val data : List<Data> = arrayListOf(),
)

