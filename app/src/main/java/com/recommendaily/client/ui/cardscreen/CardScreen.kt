package com.recommendaily.client.ui.cardscreen

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
import com.recommendaily.client.ui.cardscreen.components.DraggableCard
import com.recommendaily.client.ui.cardscreen.components.UnderCardArrows
import com.recommendaily.client.ui.navigation.components.NavRoots
import com.recommendaily.client.viewmodel.CardScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(navController: NavController, vm: CardScreenVM) {
    val data = vm.getCardsData()
//    val isUrlsLoaded = remember { mutableStateOf(false) }
    val imageUrls = vm.getImages()
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
                        //imageUrls = listOf()
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
                        //imageUrls.clear()
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
            Box(modifier = Modifier.fillMaxWidth()) {
           //     if (isUrlsLoaded.value && imageUrls.size >= data.size)
                    data.forEachIndexed { index, cardData ->
                        DraggableCard(data = cardData, imageUrl = imageUrls[index])
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



































