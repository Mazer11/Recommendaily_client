package com.recommendaily.client.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.recommendaily.client.ui.cardscreen.CardScreen
import com.recommendaily.client.ui.navigation.components.NavRoots
import com.recommendaily.client.ui.recommendation.RecommendationScreen
import com.recommendaily.client.ui.settings.SettingsScreen
import com.recommendaily.client.viewmodel.CardScreenVM

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: CardScreenVM
) {
    val animDuration = 400
    AnimatedNavHost(
        navController = navController,
        startDestination = NavRoots.CardRoot.route
    ) {
        composable(
            route = NavRoots.CardRoot.route,
            exitTransition = {
                when (targetState.destination.route) {
                    NavRoots.SettingsRoot.route ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    NavRoots.RecommendationRoot.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    NavRoots.SettingsRoot.route ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    NavRoots.RecommendationRoot.route -> {
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            }
        ) {
            CardScreen(navController = navController, vm = viewModel)
        }

        composable(
            route = NavRoots.RecommendationRoot.route,
            exitTransition = {
                when (targetState.destination.route) {
                    NavRoots.CardRoot.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            popEnterTransition = {
                when(initialState.destination.route){
                    NavRoots.CardRoot.route -> {
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
            route = NavRoots.SettingsRoot.route,
            exitTransition = {
                when(targetState.destination.route){
                    NavRoots.CardRoot.route -> {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(animDuration)
                        )
                    }
                    else -> null
                }
            },
            popEnterTransition = {
                when(initialState.destination.route){
                    NavRoots.CardRoot.route -> {
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