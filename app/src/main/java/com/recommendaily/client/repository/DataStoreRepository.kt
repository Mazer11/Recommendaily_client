package com.recommendaily.client.repository

import android.content.Context
import android.util.Log
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val DATASTORE_NAME = "recommendaily_datastore"
const val THEME_PREFERENCE = "theme"

class DataStoreRepository(
   val context: Context
) {
    private object PreferenceKey {
        val theme_key = booleanPreferencesKey(THEME_PREFERENCE)
    }

    private val Context.dataStore by preferencesDataStore(
        name = DATASTORE_NAME
    )

    private val readFromDataStore: Flow<Boolean> = context.dataStore.data
        .catch{ exception ->
            if (exception is IOException){
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

    suspend fun editThemePreference(){
        context.dataStore.edit { pref ->
            pref[PreferenceKey.theme_key] = !pref[PreferenceKey.theme_key]!!
        }
    }

}