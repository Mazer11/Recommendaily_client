package com.recommendaily.client.ui.navigation.components

sealed class NavRoots(
    val route: String,
){
    object CardRoot: NavRoots("CardScreen")
    object RecommendationRoot: NavRoots("RecommendationScreen")
    object SettingsRoot: NavRoots("SettingsScreen")
}
