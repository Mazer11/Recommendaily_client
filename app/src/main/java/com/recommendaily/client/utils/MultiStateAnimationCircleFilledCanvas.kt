package com.recommendaily.client.utils

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MultiStateAnimationCircleFilledCanvas(
    color: Color = MaterialTheme.colorScheme.tertiary,
    radiusEnd: Float = 200f
) {

    val transition = rememberInfiniteTransition()
    val floatAnim by transition.animateFloat(
        initialValue = 10f,
        targetValue = radiusEnd,
        animationSpec = infiniteRepeatable(tween(1200), RepeatMode.Reverse)
    )
    Canvas(modifier = Modifier.padding(16.dp)){

        val centerOffset = Offset(10f, 10f)
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = floatAnim,
            center = centerOffset
        )
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = floatAnim/2,
            center = centerOffset
        )
        drawCircle(
            color = color.copy(alpha = 0.8f),
            radius = floatAnim/4,
            center = centerOffset
        )

    }

}



























