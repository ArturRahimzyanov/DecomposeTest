package com.example.decomposetest.presentation.ItemsGraph

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.decomposetest.di.Repository
import com.example.decomposetest.presentation.ItemsGraph.DetailsComponent.DefaultDetailsComponent
import com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent.DefaultItemsListComponent
import kotlinx.parcelize.Parcelize

class ItemsGraphComponentImpl(
    private val repository: Repository,
    componentContext: ComponentContext,
): ItemsGraphComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack : Value<ChildStack<*, ItemsGraphComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.ItemsListChild,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): ItemsGraphComponent.Child =
        when (config) {
            is Config.ItemsListChild -> ItemsGraphComponent.Child.ItemsListChild(
                DefaultItemsListComponent(componentContext, repository)
            )

            is Config.ItemDetailsChild -> ItemsGraphComponent.Child.DetailsChild(
                DefaultDetailsComponent(componentContext)
            )
        }


    override val childStack: Value<ChildStack<*, ItemsGraphComponent.Child>> = stack

    @Parcelize
    sealed class Config: Parcelable {
        @Parcelize
        data object ItemsListChild: Config()

        @Parcelize
        data object ItemDetailsChild: Config()
    }
}