package com.example.decomposetest.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.di.Repository
import com.example.decomposetest.presentation.ItemsGraph.ItemsGraphComponentImpl
import com.example.decomposetest.presentation.card.DefaultCardsComponent
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    private val repository: Repository,
    componentContext: ComponentContext,
): RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Cards,
            handleBackButton = true,
            childFactory = ::child,
        )

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.ItemsGraph -> RootComponent.Child.ItemsGraphChild(
                ItemsGraphComponentImpl(repository, componentContext)
            )
            is Config.Cards -> RootComponent.Child.CardsChild(DefaultCardsComponent(componentContext))
        }

    override fun onItemsGraphsClicked() {
        navigation.bringToFront(Config.ItemsGraph)
    }

    override fun onCardsTabClicked() {
        navigation.bringToFront(Config.Cards)
    }

    @Parcelize
    private sealed interface Config : Parcelable {
        @Parcelize
        data object ItemsGraph : Config

        @Parcelize
        data object Cards : Config
    }

}