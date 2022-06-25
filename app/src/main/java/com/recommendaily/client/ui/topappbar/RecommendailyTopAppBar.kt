package com.recommendaily.client.ui.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.ui.navigation.components.NavRoutes
import com.recommendaily.client.ui.theme.AppTypography

@Composable
fun RecommendailyTopAppBar(
    navController: NavController,
    leftNavButtonRoute: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = AppTypography.headlineSmall.fontFamily
                        )
                    ){
                        append("Recommen")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontFamily = AppTypography.headlineSmall.fontFamily
                    )
                    ) {
                        append("daily")
                    }
                }
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(NavRoutes.SettingsRoute.route)
            }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(leftNavButtonRoute)
            }
            ) {
                when(leftNavButtonRoute) {
                    NavRoutes.RecommendationRoute.route -> Icon(
                        imageVector = Icons.Outlined.Recommend,
                        contentDescription = null
                    )
                    else -> Icon(
                        imageVector = Icons.Outlined.AddCard,
                        contentDescription = null
                    )
                }
            }
        }
    )
}