package com.example.decomposetest.presentation.ItemsGraph.DetailsComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.decomposetest.R
import com.example.decomposetest.presentation.theme.back

@Composable
fun DetailsChildScreen(component: DetailsComponent, modifier: Modifier) {

    val state = component.model.subscribeAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(48.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = stringResource(R.string.details_info),
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start,
            maxLines = 1,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            ),
        )

        Spacer(modifier = Modifier.height(32.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(state.value.data?.images?.original?.url)
                .scale(Scale.FIT)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(back),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.value.data?.title ?: stringResource(R.string.source_hidden),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.value.data?.source ?: stringResource(R.string.source_hidden),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.value.data?.trendingDatetime ?: stringResource(R.string.date_hidden),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.value.data?.url ?: stringResource(R.string.source_hidden),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.value.data?.slug ?: stringResource(R.string.source_hidden),
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
            )
        }
    }

}


