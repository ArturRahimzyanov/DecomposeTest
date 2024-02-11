package com.example.decomposetest.presentation.ItemsGraph.DetailsComponent

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.domain.repository.Repository

class DefaultDetailsComponent(
    componentContext: ComponentContext,
    repository: Repository,
)
    : DetailsComponent, ComponentContext by componentContext {


    override val model: Value<DetailsComponent.Model> = MutableValue(DetailsComponent.Model(data = repository.data, error = null))

}