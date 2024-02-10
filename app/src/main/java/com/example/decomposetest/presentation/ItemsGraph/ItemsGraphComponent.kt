package com.example.decomposetest.presentation.ItemsGraph

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.presentation.ItemsGraph.DetailsComponent.DetailsComponent
import com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent.ItemsListComponent

interface ItemsGraphComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class ItemsListChild(val component: ItemsListComponent): Child()
        class DetailsChild(val component: DetailsComponent): Child()
    }
}

