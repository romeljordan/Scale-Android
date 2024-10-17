package com.demo.app.data.core.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.demo.app.data.core.PreferencesKey
import com.demo.app.data.core.datasource.AuthRemoteDataSourceImpl
import com.demo.app.domain.core.model.Session
import com.demo.app.domain.core.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSourceImpl,
    private val dataStore: DataStore<Preferences>
): AuthRepository {
    override suspend fun login(username: String, password: String): Session {
        val response = dataSource.login(username, password)
        return if (response.isSuccessful) {
            response.body()?.let {
                dataStore.edit { preference ->
                    preference[stringPreferencesKey(PreferencesKey.SESSION_KEY)] = it.sessionId.toString()
                }
                Log.i("checker", "login: ${it.userId}, ${it.sessionId} | ${it.success}")
                it.toDomainModel()
            } ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun signUp(username: String, password: String): Boolean {
        val response = dataSource.signup(username, password)
        return if (response.isSuccessful) {
            response.body()?.success ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun logout(userId: Int): Boolean {
        val response = dataSource.logout(userId)
        return if (response.isSuccessful) {
            dataStore.edit { preference ->
                preference[stringPreferencesKey(PreferencesKey.SESSION_KEY)] = ""
            }
            response.body()?.success ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun session(sessionId: Int): Session {
        val response = dataSource.session(sessionId)
        return if (response.isSuccessful) {
            response.body()?.toDomainModel() ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun logs(userId: Int): List<String> { // TODO: update to domain model
        val response = dataSource.logs(userId)
        return if (response.isSuccessful) {
            response.body()?.logs ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun log(userId: Int, jsonLog: String): Boolean {
        val response = dataSource.log(userId, jsonLog)
        return if (response.isSuccessful) {
            response.body()?.success ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }
}
