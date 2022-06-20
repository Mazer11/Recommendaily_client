package com.recommendaily.client.ui.cardscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.recommendaily.client.R

@Composable
fun UnderCardArrows() {
    //Arrows under the card
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            contentAlignment = Alignment.CenterStart,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_curlyarrow_left),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )

            Text(
                text = stringResource(R.string.like),
                maxLines = 1,
                modifier = Modifier.padding(start = 28.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Box(
            contentAlignment = Alignment.CenterEnd,
        ) {
            Text(
                text = stringResource(R.string.dislike),
                maxLines = 1,
                modifier = Modifier.padding(end = 28.dp),
                color = MaterialTheme.colorScheme.tertiary
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_curlyarrow_right),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}