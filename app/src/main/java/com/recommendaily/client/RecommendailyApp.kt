package com.recommendaily.client

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecommendailyApp: Application() {

    val isDarkTheme = mutableStateOf(false)

    fun switchAppTheme(){
        isDarkTheme.value = !isDarkTheme.value
    }

}