package com.recommendaily.client.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.recommendaily.client.ui.cardscreen.CardScreen
import com.recommendaily.client.ui.navigation.components.NavRoutes
import com.recommendaily.client.ui.recommendation.RecommendationScreen
import com.recommendaily.client.ui.settings.SettingsScreen
import com.recommendaily.client.viewmodel.CardScreenVM

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController
) {
    val animDuration = 300
    val viewModel = CardScreenVM()

    AnimatedNavHost(
        navController = navController,
        startDestination = NavRoutes.CardRoute.route
    ) {
        composable(
            route = NavRoutes.CardRoute.route,
            exitTransition = {
                when (targetState.destination.route) {
                    NavRoutes.SettingsRoute.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    NavRoutes.RecommendationRoute.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            enterTransition = {
                when (initialState.destination.route) {
                    NavRoutes.SettingsRoute.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    NavRoutes.RecommendationRoute.route -> {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            }
        ) {
            CardScreen(
                navController = navController,
                vm = viewModel
            )
        }

        composable(
            route = NavRoutes.RecommendationRoute.route,
            exitTransition = {
                when (targetState.destination.route) {
                    NavRoutes.CardRoute.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            enterTransition = {
                when (initialState.destination.route) {
                    NavRoutes.CardRoute.route -> {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            }
        ) {
            RecommendationScreen(navController = navController)
        }

        composable(
            route = NavRoutes.SettingsRoute.route,
            exitTransition = {
                when (targetState.destination.route) {
                    NavRoutes.CardRoute.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            enterTransition = {
                when (initialState.destination.route) {
                    NavRoutes.CardRoute.route -> {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            }
        ) {
            SettingsScreen(navController = navController)
        }
    }
}