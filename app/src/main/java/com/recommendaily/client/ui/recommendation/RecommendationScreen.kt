package com.recommendaily.client.ui.recommendation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.ui.cardscreen.components.DragResult
import com.recommendaily.client.ui.navigation.components.NavRoutes
import com.recommendaily.client.ui.recommendation.component.RecommendationItem
import com.recommendaily.client.ui.theme.AppTypography
import com.recommendaily.client.ui.topappbar.RecommendailyTopAppBar
import com.recommendaily.client.viewmodel.RecommendationVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationScreen(navController: NavController) {
    val vm = RecommendationVM()
    val recms = vm.recommendations.value

    Scaffold(
        topBar = {
            RecommendailyTopAppBar(
                navController = navController,
                leftNavButtonRoute = NavRoutes.CardRoute.route
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item {
                Text(
                    text = stringResource(R.string.recommendations_for_you),
                    style = AppTypography.titleSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(recms.toList()) { data ->
                if (data.swipeResult == DragResult.NONE)
                    RecommendationItem(
                        model = data,
                        onRate = { rating ->
                            data.swipeResult = rating
                            vm.rated.value.add(data)
                        }
                    )
            }
        }
    }
}