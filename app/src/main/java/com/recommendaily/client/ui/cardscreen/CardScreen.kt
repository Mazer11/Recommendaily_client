package com.recommendaily.client.ui.cardscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.model.CardData
import com.recommendaily.client.ui.cardscreen.components.CardContent
import com.recommendaily.client.ui.cardscreen.components.DraggableCard
import com.recommendaily.client.ui.cardscreen.components.UnderCardArrows
import com.recommendaily.client.ui.navigation.components.NavRoots
import com.recommendaily.client.viewmodel.CardScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(navController: NavController) {
    val vm = CardScreenVM()
    val cards = vm.cardData.observeAsState()

//    val isUrlsLoaded = remember { mutableStateOf(false) }
    //val imageUrls = remember { mutableStateListOf<String>() }
//    vm.downloadImageUrls(data = data, callBack = object : DownloadImagesUrlsCallback {
//        override fun onCallback(value: MutableList<String>) {
//            imageUrls.addAll(value)
//            isUrlsLoaded.value = true
//        }
//    })

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.recommendaily))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(NavRoots.SettingsRoot.route)
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(NavRoots.RecommendationRoot.route)
                    }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_recommendation),
                            contentDescription = null
                        )
                    }
                }
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
            Box() {
                val isListEmpty = remember { mutableStateOf(false) }
                val swipedCards = mutableListOf<CardData>()

                val cardIterator: MutableIterator<CardData>?

                if (cards.value!!.size > 0) {
                    cardIterator = cards.value?.iterator()
                    while (cardIterator?.hasNext() == true) {
                        val iterator = cardIterator.next()
                        DraggableCard(
                            data = iterator,
                            onSwiped = { swipeResult, data ->
                                //data.swipeResult = swipeResult
                                //swipedCards.add(data)
                                if (cardIterator.hasNext()) {
                                    cardIterator.remove()
                                    if (!cardIterator.hasNext())
                                        isListEmpty.value = true
                                }
                            },
                        ) {
                            CardContent(data = iterator)
                        }
                    }
                }
            }

            //Footer Button
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(vertical = 16.dp)
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