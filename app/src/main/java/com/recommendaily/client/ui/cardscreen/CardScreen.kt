package com.recommendaily.client.ui.cardscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.ui.navigation.components.NavRoots
import com.recommendaily.client.ui.theme.AppTypography
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.recommendaily))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(NavRoots.SettingsRoot.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(NavRoots.RecommendationRoot.route) }) {
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

            //Arrows under the card
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_curlyarrow_left),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )

                    Text(
                        text = stringResource(R.string.dislike),
                        maxLines = 1,
                        modifier = Modifier.padding(start = 28.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                Box(
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Text(
                        text = stringResource(R.string.like),
                        maxLines = 1,
                        modifier = Modifier.padding(end = 28.dp),
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_curlyarrow_right),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            }

            //Card
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {

                //Header
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Title", style = AppTypography.displaySmall)
                    Text(text = "Subtitle", style = AppTypography.titleSmall)
                }

                //Image
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ) {

                }

                //Info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Release date")
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box {
                            Text(text = "rate%")
                            Icon(
                                painter = painterResource(id = R.drawable.ic_rate),
                                contentDescription = null,
                                modifier = Modifier.padding(start = 45.dp)
                            )
                        }
                        Text(text = "players amount")
                        Text(text = "price$")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "An online and local party game of teamwork and betrayal for 4-10 players...in space! About This GamePlay with 4-10 player online or via local WiFi as you attempt to prepare your spacesh")
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            //Footer Button
            ExtendedFloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_neutral),
                    contentDescription = null
                )

                Text(text = stringResource(R.string.dont_know))
            }
        }
    }
}

































