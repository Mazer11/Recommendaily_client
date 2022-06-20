package com.recommendaily.client.ui.recommendation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.recommendaily.client.ui.cardscreen.components.DragResult
import com.recommendaily.client.ui.navigation.components.NavRoutes
import com.recommendaily.client.ui.recommendation.component.RecommendationItem
import com.recommendaily.client.ui.theme.AppTypography
import com.recommendaily.client.ui.topappbar.RecommendailyTopAppBar
import com.recommendaily.client.viewmodel.CardScreenVM
import com.recommendaily.client.viewmodel.RecommendationVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecommendationScreen(navController: NavController) {

    val vm = RecommendationVM()
    val recms = vm.recommendations.observeAsState()

    Scaffold(
        topBar = {
            RecommendailyTopAppBar(
                navController = navController,
                leftNavButtonRoute = NavRoutes.CardRoute.route
            )
        }
    ) {
        LazyColumn() {
            item {
                Text(
                    text = "Recommendations for you",
                    style = AppTypography.titleSmall
                )
            }
            if (recms.value != null)
                items(recms.value!!.toList()) { data ->
                    if (data.swipeResult == DragResult.NONE)
                        RecommendationItem(
                            model = data,
                            onRate = { rating ->
                                data.swipeResult = rating
                                vm.rated.value?.add(data)
                            }
                        )
                }
        }
    }
}

@Preview(name = "recommendations preview", showSystemUi = true, showBackground = true)
@Composable
fun RecommendationScreenPreview() {
    RecommendationScreen(navController = rememberNavController())
}



























