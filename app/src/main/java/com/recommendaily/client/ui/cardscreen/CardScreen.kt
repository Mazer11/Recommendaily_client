package com.recommendaily.client.ui.cardscreen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.ui.cardscreen.components.CardContent
import com.recommendaily.client.ui.cardscreen.components.DraggableCard
import com.recommendaily.client.ui.cardscreen.components.UnderCardArrows
import com.recommendaily.client.ui.navigation.components.NavRoutes
import com.recommendaily.client.ui.topappbar.RecommendailyTopAppBar
import com.recommendaily.client.viewmodel.CardScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(
    navController: NavController,
    vm: CardScreenVM
) {
    val idCardToHide = remember { mutableStateListOf<Int>() }
    vm.setCardNumberCounterToZero()
    val cData = remember { mutableStateOf(vm.cardData.value) }

//    val isUrlsLoaded = remember { mutableStateOf(false) }
//    val imageUrls = remember { mutableStateListOf<String>() }
//    vm.downloadImageUrls(data = data, callBack = object : DownloadImagesUrlsCallback {
//        override fun onCallback(value: MutableList<String>) {
//            imageUrls.addAll(value)
//            isUrlsLoaded.value = true
//        }
//    })

    Scaffold(
        topBar = {
            RecommendailyTopAppBar(
                navController = navController,
                leftNavButtonRoute = NavRoutes.RecommendationRoute.route
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            UnderCardArrows()

            val recomposition = remember { mutableStateOf(false) }

            if (recomposition.value)
                Box {
                    cData.value.forEachIndexed { index, card ->
                        DraggableCard(
                            data = card,
                            index = index,
                            idToHide = idCardToHide.toList(),
                            onSwiped = { swipeResult, data ->
                                data.swipeResult = swipeResult
                                vm.memorizeSwipeResult(dataToMemorize = data)
                                vm.setCounterOrLoadNewCards(
                                    idCardToHide,
                                    recomposition
                                )
                            },
                        ) {
                            CardContent(data = card)
                        }
                    }
                }
            else
                Box {
                    cData.value.forEachIndexed { index, card ->
                        DraggableCard(
                            data = card,
                            index = index,
                            idToHide = idCardToHide.toList(),
                            onSwiped = { swipeResult, data ->
                                data.swipeResult = swipeResult
                                vm.memorizeSwipeResult(dataToMemorize = data)
                                vm.setCounterOrLoadNewCards(
                                    idCardToHide,
                                    recomposition
                                )
                            },
                        ) {
                            CardContent(data = card)
                        }
                    }
                }

            //Footer Button
            ExtendedFloatingActionButton(
                onClick = {
                    idCardToHide.add(vm.currentCardId)
                    vm.currentCardId -= 1
                    Log.d("Testing", idCardToHide.size.toString())
                    vm.setCounterOrLoadNewCards(
                        idCardToHide,
                        recomposition
                    )
                    Log.d("Testing", idCardToHide.size.toString())
                },
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_neutral),
                    contentDescription = null
                )

                Text(text = stringResource(R.string.dont_know))

            }
        }
    }
}