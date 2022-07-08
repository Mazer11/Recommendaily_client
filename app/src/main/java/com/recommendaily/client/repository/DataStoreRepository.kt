package com.recommendaily.client.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Singleton

const val DATASTORE_NAME = "recommendaily_datastore"
const val THEME_PREFERENCE = "theme"

@Singleton
class DataStoreRepository(
    private val context: Context
) {
    companion object {
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    private object PreferenceKey {
        val theme_key = booleanPreferencesKey(THEME_PREFERENCE)
    }

    val readFromDataStore: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val theme = preferences[PreferenceKey.theme_key] ?: false
            theme
        }

    suspend fun editThemePreference() {
        context.dataStore.edit { pref ->
            if (pref[PreferenceKey.theme_key] == null)
                pref[PreferenceKey.theme_key] = false
            else
                pref[PreferenceKey.theme_key] = !pref[PreferenceKey.theme_key]!!
        }
    }
}