package com.recommendaily.client.ui.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.ui.navigation.components.NavRoutes

@Composable
fun RecommendailyTopAppBar(
    navController: NavController,
    leftNavButtonRoute: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.recommendaily))
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