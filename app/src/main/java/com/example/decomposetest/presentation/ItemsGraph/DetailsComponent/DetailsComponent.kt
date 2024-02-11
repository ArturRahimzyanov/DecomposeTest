package com.example.decomposetest.presentation.ItemsGraph.DetailsComponent

import com.arkivanov.decompose.value.Value
import com.example.decomposetest.domain.model.Data

interface DetailsComponent {

    val model: Value<Model>

    data class Model(
        val data: Data?,
        val error: String?,
    )
}


