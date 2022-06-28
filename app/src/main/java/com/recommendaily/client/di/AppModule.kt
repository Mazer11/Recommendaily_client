package com.recommendaily.client.di

import android.content.Context
import com.recommendaily.client.RecommendailyApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context): RecommendailyApp {
        return app as RecommendailyApp
    }

}