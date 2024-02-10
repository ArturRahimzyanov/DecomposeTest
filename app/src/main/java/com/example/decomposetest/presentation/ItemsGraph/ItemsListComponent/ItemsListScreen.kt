package com.example.decomposetest.presentation.ItemsGraph.ItemsListComponent

import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.decomposetest.R
import com.example.decomposetest.model.Data
import com.example.decomposetest.presentation.theme.Dark_ColdSteelLight_1
import com.example.decomposetest.presentation.theme.card
import com.example.decomposetest.presentation.theme.input
import com.example.decomposetest.presentation.theme.outline
import com.example.decomposetest.presentation.theme.surface

@Composable
fun ItemsListScreen(component: ItemsListComponent, modifier: Modifier) {

    val state = component.model.subscribeAsState()

    Log.e("logs", "screen ${state.value}")


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

        state.value.exampleJson2KtKotlin?.let {
            Log.e("logs", "ui ${it}")

            items(it.toList()) {
                GifImage(it)
            }
        }



    }
}

@Composable
fun GifImage(
    data: Data,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = data.url).apply(block = {
                size(coil.size.Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
    )
}
