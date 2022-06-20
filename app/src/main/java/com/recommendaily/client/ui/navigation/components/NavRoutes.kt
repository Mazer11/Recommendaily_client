package com.recommendaily.client.ui.navigation.components

sealed class NavRoutes(
    val route: String,
){
    object CardRoute: NavRoutes("CardScreen")
    object RecommendationRoute: NavRoutes("RecommendationScreen")
    object SettingsRoute: NavRoutes("SettingsScreen")
}
