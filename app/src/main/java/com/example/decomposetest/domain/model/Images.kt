package com.example.decomposetest.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images (
    @SerialName("original") val original : Original = Original(),
)