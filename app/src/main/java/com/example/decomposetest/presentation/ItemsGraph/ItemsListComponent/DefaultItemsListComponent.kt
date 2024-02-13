package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.update
import com.example.decomposetest.domain.model.Data
import com.example.decomposetest.domain.repository.Repository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class DefaultItemsListComponent(
    componentContext: ComponentContext,
    private val navigation: (Data) -> Unit,
) : ItemsListComponent, ComponentContext by componentContext {

    override val model = MutableValue(ItemsListComponent.Model(text = "", persistentListOf()))
    private val repository: Repository by inject(Repository::class.java)
    private val coroutineScope: CoroutineScope by inject(CoroutineScope::class.java)
    private var job: Job? = null

    override fun onItemClicked(data: Data) {
        navigation(data)
    }

    init {
        coroutineScope.launch {
            getInitGifs()
        }
    }

    override fun textChanged(newString: String) {

        job?.cancel()

        model.update { it.copy(text = newString) }

        job = coroutineScope.launch {

            delay(500)

            coroutineScope.async {
                repository.getGifs(limit = 20, offset = 0, model.value.text)
            }.await().let { it1 ->
                model.update {
                    it.copy(dataPersistentList = it1.data.toPersistentList())
                }
            }
        }
    }

    private suspend fun getInitGifs() {
        coroutineScope.async {
            repository.getGifs(limit = 20, offset = 0, "android")
        }.await().let { it1 ->
            model.update {
                it.copy(dataPersistentList = model.value.dataPersistentList?.addAll(it1.data.toPersistentList()))
            }
        }
    }
}


