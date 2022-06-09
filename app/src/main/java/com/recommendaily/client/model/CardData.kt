package com.recommendaily.client.model

import com.recommendaily.client.ui.cardscreen.components.DragResult

data class CardData(
    val id: Int,
    val title: String,
    val subtitle: String,
    val release_date: String,
    val popularity: String,
    val developer: String,
    val user_count: String,
    val price: String,
    val description: String,
    val imageUrl: String,
    var swipeResult: DragResult = DragResult.NONE
)