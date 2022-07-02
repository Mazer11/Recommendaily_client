package com.recommendaily.client

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlin.coroutines.coroutineContext

@HiltAndroidApp
class RecommendailyApp: Application() {



    val isDarkTheme = mutableStateOf(false)

    fun switchAppTheme(){
        isDarkTheme.value = !isDarkTheme.value
    }

}