package com.recommendaily.client.ui.recommendation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.cardscreen.components.DragResult
import com.recommendaily.client.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationAlertDialog(
    model: CardData,
    onRated: (DragResult) -> Unit,
    onDismissRequest: () -> Unit
) {
    val categories = model.subtitle.split(" ")

    AlertDialog(
        shape = RoundedCornerShape(12.dp),
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(8.dp)
            ) {
                Text(text = model.title, style = AppTypography.headlineSmall)
                Text(text = model.developer, style = AppTypography.titleMedium)
                Text(text = model.release_date, style = AppTypography.titleSmall)
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
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
                        Text(text = model.popularity, style = AppTypography.bodyLarge)
                    }
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_users),
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = model.user_count, style = AppTypography.bodyLarge)
                    }
                    Text(text = model.price, style = AppTypography.bodyLarge)
                }
                Text(
                    text = model.description,
                    style = AppTypography.bodyMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                FlowRow(
                    mainAxisSpacing = 4.dp
                ) {
                    for (category in categories)
                        ElevatedAssistChip(
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
            }
        },
        confirmButton = {
            FilledTonalButton(onClick = {
                onRated(DragResult.LIKES)
                onDismissRequest()
            }) {
                Text(text = stringResource(id = R.string.like))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = {
                onRated(DragResult.DISLIKES)
                onDismissRequest()
            }) {
                Text(text = stringResource(id = R.string.dislike))
            }
        },
        onDismissRequest = { onDismissRequest() }
    )

}