package com.recommendaily.client.ui.recommendation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.cardscreen.components.DragResult
import com.recommendaily.client.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationItem(
    model: CardData,
    onRate: (DragResult) -> Unit
) {
    val dialogState = remember { mutableStateOf(false) }
    val isRated = remember { mutableStateOf(false) }

    val cardHeight = if (isRated.value) 0.dp else 70.dp
    val cardPadding = if (isRated.value) 0.dp else 8.dp

    OutlinedCard(
        modifier = Modifier
            .padding(all = cardPadding)
            .fillMaxWidth()
            .height(cardHeight),
        onClick = {
            dialogState.value = true
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = model.title,
                style = AppTypography.titleSmall,
                maxLines = 2,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
                    .padding(end = 88.dp)
            )
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .align(Alignment.CenterEnd),
                color = MaterialTheme.colorScheme.background
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(model.imageUrl)
                            .error(R.drawable.ic_launcher_foreground)
                            .crossfade(true)
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

            }
        }
    }

    if (dialogState.value)
        RecommendationAlertDialog(
            model = model,
            onRated = {
                onRate(it)
                isRated.value = true
            },
            onDismissRequest = {
                dialogState.value = !dialogState.value
            }
        )
}