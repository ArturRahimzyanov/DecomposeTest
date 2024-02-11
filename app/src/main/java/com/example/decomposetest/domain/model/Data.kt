package com.example.decomposetest.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data (
    @SerialName("id"                         ) val id                       : String,
    @SerialName("url"                        ) val url                      : String?    = null,
    @SerialName("slug"                       ) val slug                     : String?    = null,
    @SerialName("source"                     ) val source                   : String?    = null,
    @SerialName("title"                      ) val title                    : String?    = null,
    @SerialName("trending_datetime"          ) val trendingDatetime         : String?    = null,
    @SerialName("images"                     ) val images                   : Images?    = Images(),
)