package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import com.arkivanov.decompose.value.Value
import com.example.decomposetest.model.Data
import kotlinx.collections.immutable.PersistentList

interface ItemsListComponent {

    val model: Value<Model>

    fun onItemClicked(item: String)

    fun textChanged(newString: String)

    data class Model(
        val text: String,
        val exampleJson2KtKotlin: PersistentList<Data>?,
    )
}