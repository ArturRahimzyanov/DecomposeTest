package com.example.decomposetest.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.presentation.ItemsGraph.ItemsGraphComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onItemsGraphsClicked()
    fun onCardsTabClicked()

    sealed class Child {
        class ItemsGraphChild(val component: ItemsGraphComponent) : Child()
        data object CardsChild : Child()
    }
}