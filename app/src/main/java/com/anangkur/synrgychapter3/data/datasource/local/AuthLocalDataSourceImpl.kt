package com.anangkur.synrgychapter3.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anangkur.synrgychapter3.data.datasource.AuthLocalDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

class AuthLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : AuthLocalDataSource {

    companion object {
        const val KEY_TOKEN = "token"
        private val DATASTORE_KEY_TOKEN = stringPreferencesKey(KEY_TOKEN)
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_TOKEN] = token
        }
    }

    override suspend fun loadToken(): String? {
        return dataStore.data.map { preferences ->
            preferences[DATASTORE_KEY_TOKEN]
        }.firstOrNull()
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences[DATASTORE_KEY_TOKEN] = ""
        }
    }
}