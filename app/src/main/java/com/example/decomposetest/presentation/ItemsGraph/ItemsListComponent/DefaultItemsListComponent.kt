package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import androidx.compose.ui.unit.IntOffset
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.decomposetest.domain.repository.Repository
import com.example.decomposetest.domain.model.Data
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DefaultItemsListComponent (
    componentContext: ComponentContext,
    private val coroutineScope: CoroutineScope,
    private val repository: Repository,
    private val navigation : (Data) -> Unit,
) : ItemsListComponent, ComponentContext by componentContext {

    private var offset = 0

    override val model = MutableValue(ItemsListComponent.Model(text = "", persistentListOf()))

    override fun onItemClicked(data: Data) {
        navigation(data)
    }

    init {
        coroutineScope.launch {
           getGifs()
        }
    }

    override fun textChanged(newString: String) {
        model.update { it.copy(text = newString) }
    }

    override fun pagination() {
        coroutineScope.launch {
            offset+= 10
            getGifs()
        }
    }

    private suspend fun getGifs(){
        coroutineScope.async {
            repository.getGifs(limit = 10, offset = offset)
        }.await().let { it1 ->
            model.update {
                it.copy(dataPersistentList = model.value.dataPersistentList?.addAll(it1.data.toPersistentList()) )
            }
        }
    }
}


