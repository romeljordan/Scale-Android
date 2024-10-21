package com.demo.app.data.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.demo.app.data.core.PreferencesKey
import com.demo.app.domain.core.repository.AccessTokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): AccessTokenRepository {
    override suspend fun saveAccessToken(token: String): Boolean {
        return try {
            dataStore.edit { preference ->
                preference[stringPreferencesKey(PreferencesKey.ACCESS_TOKEN)] = token
            }
            true
        } catch (e: Throwable) {
            false
        }
    }

    override suspend fun fetchAccessToken(): Flow<String> {
        return dataStore.data.map { preference ->
            preference[stringPreferencesKey(PreferencesKey.ACCESS_TOKEN)] ?: ""
        }
    }
}
