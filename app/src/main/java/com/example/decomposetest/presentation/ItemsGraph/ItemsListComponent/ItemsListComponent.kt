package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import com.arkivanov.decompose.value.Value
import com.example.decomposetest.domain.model.Data
import kotlinx.collections.immutable.PersistentList

interface ItemsListComponent {

    val model: Value<Model>

    fun onItemClicked(data: Data)

    fun textChanged(newString: String)

    fun pagination()


    data class Model(
        val text: String,
        val dataPersistentList: PersistentList<Data>?,
    )
}