package com.recommendaily.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.recommendaily.client.ui.navigation.NavGraph
import com.recommendaily.client.ui.theme.Recommendailytheme
import com.recommendaily.client.viewmodel.CardScreenVM

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Recommendailytheme {
                val navController = rememberAnimatedNavController()
                NavGraph(navController)
            }
        }
    }
}