package com.example.decomposetest.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
    @SerialName("slug") val slug: String,
    @SerialName("source") val source: String,
    @SerialName("title") val title: String,
    @SerialName("trending_datetime") val trendingDatetime: String,
    @SerialName("images") val images: Images = Images(),
)