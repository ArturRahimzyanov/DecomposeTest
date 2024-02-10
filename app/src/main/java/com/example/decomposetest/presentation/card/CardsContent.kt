package com.example.decomposetest.presentation.card

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.decomposetest.presentation.ItemsGraph.DetailsComponent.DetailsComponent

@Composable
fun CardsContent(component: CardsComponent, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text("CardsContent ${component.hashCode()}")
    }
}