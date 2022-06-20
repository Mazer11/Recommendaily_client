package com.recommendaily.client.ui.recommendation.component

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.cardscreen.components.DragResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationItem(
    model: CardData,
    onRate: (DragResult) -> Unit
) {

    OutlinedCard(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
            .height(70.dp),
        onClick = { /*TODO Alert Dialog with rating opportunity*/ }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = model.title,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp))
                    .fillMaxHeight()
                    .width(50.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(model.imageUrl)
                            .crossfade(true)
                            .error(R.drawable.ic_launcher_foreground)
                            .build(),
                        filterQuality = FilterQuality.Low,
                        contentScale = ContentScale.Fit
                    ),
                    contentDescription = "${model.title} image"
                )
            }
        }
    }

}































