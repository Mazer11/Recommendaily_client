package com.recommendaily.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.recommendaily.client.repository.DataStoreRepository
import com.recommendaily.client.ui.navigation.NavGraph
import com.recommendaily.client.ui.theme.Recommendailytheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: RecommendailyApp

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val datastore = DataStoreRepository(context)

            val themeValue = datastore.readFromDataStore.collectAsState(initial = isSystemInDarkTheme())
            application.getAppThemeFromDataStore(themeValue.value)

            Recommendailytheme(
                useDarkTheme = application.isDarkTheme.value
            ) {
                val navController = rememberAnimatedNavController()
                NavGraph(
                    navController =  navController,
                    application = application
                )
            }
        }
    }
}