
package com.example.decomposetest

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.decomposetest.domain.repository.Repository
import com.example.decomposetest.presentation.ItemsGraph.ItemGraphScreen
import com.example.decomposetest.presentation.about_app.AboutAppContent
import com.example.decomposetest.presentation.root.DefaultRootComponent
import com.example.decomposetest.presentation.root.RootComponent
import com.example.decomposetest.presentation.theme.Dark_ColdSteelLight_1
import com.example.decomposetest.presentation.theme.Dark_ColdSteelLight_4
import com.example.decomposetest.presentation.theme.DecomposeTestTheme
import com.example.decomposetest.presentation.theme.back
import com.example.decomposetest.presentation.theme.surface
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository: Repository by inject()
        val coroutineScope: CoroutineScope by inject()
        val root = DefaultRootComponent(
            repository = repository,
            componentContext = defaultComponentContext(),
            coroutineScope = coroutineScope
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val view = LocalView.current
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = back.toArgb()
                    window.navigationBarColor = surface.toArgb()
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                    WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
                }
            }

            RootContent(component = root, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    DecomposeTestTheme {
        Surface(modifier = modifier, color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Children(component = component, modifier = Modifier.weight(1F))
                BottomBar(component = component)
            }
        }
    }
}

@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.ItemsGraphChild -> ItemGraphScreen(
                component = child.component, modifier = Modifier
                    .fillMaxSize()
                    .background(back)
            )

            is RootComponent.Child.CardsChild -> AboutAppContent(
                 modifier = Modifier
                    .fillMaxSize()
                    .background(back)
            )
        }
    }
}

@Composable
private fun BottomBar(component: RootComponent) {

    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    BottomNavigation {
        BottomNavigationItem(
            modifier = Modifier.background(surface),
            selected = activeComponent is RootComponent.Child.ItemsGraphChild,
            onClick = component::onItemsGraphsClicked,
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_home),
                    contentDescription = stringResource(R.string.main),
                    tint = if(activeComponent is RootComponent.Child.ItemsGraphChild) Dark_ColdSteelLight_4 else  Dark_ColdSteelLight_1
                )
            },
        )

        BottomNavigationItem(
            modifier = Modifier.background(surface),
            selected = activeComponent is RootComponent.Child.CardsChild,
            onClick = component::onCardsTabClicked,
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(R.string.profile),
                    tint = if(activeComponent is RootComponent.Child.CardsChild) Dark_ColdSteelLight_4 else  Dark_ColdSteelLight_1
                )
            },
        )
    }
}




