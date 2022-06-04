package com.recommendaily.client.ui.cardscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowRow
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DraggableCard(
    data: CardData,
    imageUrl: String
) {
    val categories = data.subtitle.split(" ")

    //Card
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(500.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        //Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = data.title, style = AppTypography.displaySmall)
            Text(text = data.developer, style = AppTypography.titleSmall)
        }

        //Image
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(215.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .error(R.drawable.ic_launcher_foreground)
                        .crossfade(false)
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
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 4.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rate),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
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
}

































