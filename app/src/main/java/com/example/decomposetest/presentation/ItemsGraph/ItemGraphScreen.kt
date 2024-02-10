package com.example.decomposetest.presentation.ItemsGraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.example.decomposetest.presentation.ItemsGraph.DetailsComponent.DetailsChildScreen
import com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent.ItemsListScreen
import com.example.decomposetest.presentation.theme.back

@Composable
fun ItemGraphScreen(component: ItemsGraphComponent, modifier: Modifier) {

    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is ItemsGraphComponent.Child.ItemsListChild -> ItemsListScreen(
                component = child.component, modifier = Modifier
                    .fillMaxSize()
                    .background(back)
            )

            is ItemsGraphComponent.Child.DetailsChild -> DetailsChildScreen(
                component = child.component, modifier = Modifier
                    .fillMaxSize()
                    .background(back)
            )
        }
    }
}