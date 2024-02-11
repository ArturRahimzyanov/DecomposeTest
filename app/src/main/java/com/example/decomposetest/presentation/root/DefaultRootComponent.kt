package com.example.decomposetest.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.domain.repository.Repository
import com.example.decomposetest.presentation.ItemsGraph.ItemsGraphComponentImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.parcelize.Parcelize

class DefaultRootComponent(
    private val repository: Repository,
    private val coroutineScope: CoroutineScope,
    componentContext: ComponentContext,
): RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Cards,
            handleBackButton = false,
            childFactory = ::child,
        )

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.ItemsGraph -> RootComponent.Child.ItemsGraphChild(
                ItemsGraphComponentImpl(
                    repository = repository,
                    componentContext = componentContext,
                    coroutineScope = coroutineScope,
                )
            )
            is Config.Cards -> RootComponent.Child.CardsChild
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