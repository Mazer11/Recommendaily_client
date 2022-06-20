package com.recommendaily.client.ui.cardscreen.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowRow
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.theme.AppTypography
import kotlinx.coroutines.launch
import kotlin.math.abs

enum class DragResult {
    LIKES, DISLIKES, NONE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableCard(
    data: CardData,
    index: Int,
    onSwiped: (DragResult, CardData) -> Unit,
    idToHide: List<Int>,
    content: @Composable () -> Unit
) {
    //Swipe values
    val cardHeight: Dp = if(idToHide.contains(index)) 0.dp else 524.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val swipeBoundLeft = -(screenWidth.value*3)
    val swipeBoundRight = (screenWidth.value*3)
    val swipeBounds = remember { Animatable(0f) }
    swipeBounds.updateBounds(swipeBoundLeft, swipeBoundRight)

    if (abs(swipeBounds.value) < swipeBoundRight) {
        val rotationFraction = (swipeBounds.value / 60).coerceIn(-40f, 40f)
        ElevatedCard(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .padding(16.dp)
                .dragContent(
                    swipeCardValue = swipeBounds,
                    maxX = swipeBoundRight
                )
                .graphicsLayer(
                    translationX = swipeBounds.value,
                    rotationZ = rotationFraction
                ),
        ) {
            content()
        }
    } else {
        Log.d("InDraggableCard", data.title)
        val swipeResult = if (swipeBounds.value > 0) DragResult.DISLIKES else DragResult.LIKES
        onSwiped(swipeResult, data)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardContent(
    data: CardData,
) {
    val categories = data.subtitle.split(" ")

    //Header
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = data.title, style = AppTypography.headlineSmall)
        Text(text = data.developer, style = AppTypography.titleSmall)
    }

    //Image
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(185.dp),
        color = MaterialTheme.colorScheme.background
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.imageUrl)
                    .error(R.drawable.ic_launcher_foreground)
                    .crossfade(true)
                    .build(),
                filterQuality = FilterQuality.Low,
                contentScale = ContentScale.FillBounds
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )

    }

    //Info
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = data.release_date, modifier = Modifier.padding(bottom = 16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rate),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp, top = 1.dp)
                )
                Text(text = data.popularity, style = AppTypography.bodyLarge)
            }
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_users),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(text = data.user_count, style = AppTypography.bodyLarge)
            }
            Text(text = data.price, style = AppTypography.bodyLarge)
        }
        FlowRow(
            mainAxisSpacing = 8.dp
        ) {
            for (category in categories)
                ElevatedSuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            text = category,
                            style = AppTypography.labelMedium
                        )
                    },
                    shape = CircleShape
                )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Modifier.dragContent(
    swipeCardValue: Animatable<Float, AnimationVector1D>,
    maxX: Float
): Modifier = composed {
    val coroutineScope = rememberCoroutineScope()
    pointerInput(Unit) {
        this.detectDragGestures(
            onDragCancel = {
                coroutineScope.apply {
                    launch { swipeCardValue.animateTo(0f) }
                }
            },
            onDragEnd = {
                coroutineScope.apply {
                    if (abs(swipeCardValue.targetValue) < maxX / 4) {
                        launch {
                            swipeCardValue.animateTo(0f, tween(400))
                        }
//                        launch {
//                            swipeY.animateTo(0f, tween(400))
//                        }
                    } else {
                        launch {
                            if (swipeCardValue.targetValue > 0) {
                                swipeCardValue.animateTo(maxX, tween(400))
                            } else {
                                swipeCardValue.animateTo(-maxX, tween(400))
                            }
                        }
                    }
                }
            }
        ) { change, dragAmount ->
            change.consume()
            coroutineScope.apply {
                launch { swipeCardValue.animateTo(swipeCardValue.targetValue + dragAmount.x) }
            }
        }
    }
}