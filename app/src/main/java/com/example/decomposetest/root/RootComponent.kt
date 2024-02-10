package com.example.decomposetest.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.presentation.ItemsGraph.ItemsGraphComponent
import com.example.decomposetest.presentation.card.CardsComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onItemsGraphsClicked()
    fun onCardsTabClicked()

    sealed class Child {
        class ItemsGraphChild(val component: ItemsGraphComponent) : Child()
        class CardsChild(val component: CardsComponent) : Child()
    }
}