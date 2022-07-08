package com.recommendaily.client.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.recommendaily.client.R
import com.recommendaily.client.RecommendailyApp
import com.recommendaily.client.repository.DataStoreRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    application: RecommendailyApp
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val datastore = DataStoreRepository(context)

    val isDarkTheme = application.isDarkTheme

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(R.string.settings)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.dark_mode),
                )
                Switch(
                    checked = isDarkTheme.value,
                    onCheckedChange = {
                        application.switchAppTheme()
                        scope.launch {
                            datastore.editThemePreference()
                        }
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}