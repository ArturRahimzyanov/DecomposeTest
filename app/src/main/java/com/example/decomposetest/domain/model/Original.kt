package com.example.decomposetest.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Original (
    @SerialName("height"    ) val height   : String? = null,
    @SerialName("width"     ) val width    : String? = null,
    @SerialName("size"      ) val size     : String? = null,
    @SerialName("url"       ) val url      : String? = null,
)