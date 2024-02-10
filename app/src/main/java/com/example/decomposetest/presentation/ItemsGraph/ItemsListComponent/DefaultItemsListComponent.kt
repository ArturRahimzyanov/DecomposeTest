package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.decomposetest.di.Repository
import com.example.decomposetest.model.Data
import com.example.decomposetest.model.ExampleJson2KtKotlin
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf

class DefaultItemsListComponent (
    componentContext: ComponentContext,
    private val repository: Repository,
) : ItemsListComponent, ComponentContext by componentContext {

    override val model = MutableValue(ItemsListComponent.Model(text = "", null))

    override fun onItemClicked(item: String) {

    }

    override fun textChanged(newString: String) {
        model.update { it.copy(text = newString) }

        var a : PersistentList<Data> = persistentListOf()

        CoroutineScope(Dispatchers.IO).launch {

            CoroutineScope(Dispatchers.IO).async {
                repository.getGifs(newString)
            }.await().let {
                Log.e("logs", "let $it")
                a = it.toPersistentList()
            }
        }

        model.update {
            it.copy(exampleJson2KtKotlin = a)
        }
    }
}


