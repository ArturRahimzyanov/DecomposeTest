package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.decomposetest.R
import com.example.decomposetest.domain.model.Data
import com.example.decomposetest.presentation.theme.Dark_ColdSteelLight_1
import com.example.decomposetest.presentation.theme.back
import com.example.decomposetest.presentation.theme.card
import com.example.decomposetest.presentation.theme.input
import com.example.decomposetest.presentation.theme.outline
import com.example.decomposetest.presentation.theme.surface

@Composable
fun ItemsListScreen(component: ItemsListComponent, modifier: Modifier) {

    val state = component.model.subscribeAsState()

    LazyColumn(modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {

        item {
            BasicTextField(
                value = state.value.text,
                onValueChange = component::textChanged,
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth()
                    .background(surface, card)
                    .border(
                        width = 1.dp,
                        color = outline,
                        shape = RoundedCornerShape(8.dp)
                    ),

                singleLine = true,
                maxLines = 1,
                cursorBrush = SolidColor(outline),
                textStyle = input,
                decorationBox = { innerTextField ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(36.dp)
                                .weight(1f),

                            contentAlignment = Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (component.model.value.text.isEmpty()) {
                                    Text(
                                        text = stringResource(id = R.string.find),
                                        color = Dark_ColdSteelLight_1,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 1,
                                    )
                                }
                            }

                            innerTextField()
                        }
                    }
                }
            )
        }

        state.value.dataPersistentList?.let { list ->
            items(items = list, key = { it.id }) { data ->
                GifItem(data = data) {
                    component.onItemClicked(data = data)
                }
            }
        }
    }
}

@Composable

fun GifItem(
    data: Data,
    onTap: (Data) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(back)
            .clickable { onTap(data) }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.images?.original?.url ?: "https://ak.picdn.net/shutterstock/videos/1012691939/thumb/1.jpg")
                .scale(Scale.FIT)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
                .background(back),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = data.title ?:"Источник скрыт",
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
                style = input
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = data.source ?: "Источник скрыт",
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
                style = input
            )
        }
    }
}

