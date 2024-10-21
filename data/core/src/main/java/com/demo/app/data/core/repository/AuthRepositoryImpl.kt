package com.demo.app.data.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.demo.app.data.core.PreferencesKey
import com.demo.app.data.core.datasource.AuthRemoteDataSourceImpl
import com.demo.app.domain.core.error.MissingResponseBodyException
import com.demo.app.domain.core.error.ServerErrorException
import com.demo.app.domain.core.model.Session
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.domain.core.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import org.json.JSONObject
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
                    it.token?.let { accessToken ->
                        preference[stringPreferencesKey(PreferencesKey.ACCESS_TOKEN)] = accessToken
                    }
                }
                it.toDomainModel()
            } ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException("Invalid login credentials")
        }
    }

    override suspend fun signUp(username: String, password: String): Boolean {
        val response = dataSource.signup(username, password)
        return if (response.isSuccessful) {
            response.body()?.success ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException("Invalid sign up")
        }
    }

    override suspend fun logout(userId: Int): Boolean {
        val response = dataSource.logout(userId)
        return if (response.isSuccessful) {
            dataStore.edit { preference ->
                preference.remove(stringPreferencesKey(PreferencesKey.SESSION_KEY))
                preference.remove(stringPreferencesKey(PreferencesKey.ACCESS_TOKEN))
            }
            response.body()?.success ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException("Unsuccessful log out")
        }
    }

    override suspend fun session(sessionId: Int): Session {
        val response = dataSource.session(sessionId)
        return if (response.isSuccessful) {
            response.body()?.toDomainModel() ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException("Session key is not valid")
        }
    }

    override suspend fun logs(userId: Int): List<WeatherLog> {
        val response = dataSource.logs(userId)
        return if (response.isSuccessful) {
            response.body()?.let{
                val entries = mutableListOf<WeatherLog>()
                val jsonObject = JSONObject(it.logs)
                val items = jsonObject.getJSONArray("logs")
                for (i in 0..<items.length()) {
                    val item = items.getJSONObject(i)
                    val weatherLog = Gson().fromJson(item.getString("log"), WeatherLog::class.java)
                    entries.add(weatherLog)
                }
                entries.toList()
            } ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException(response.errorBody()?.string() ?: "")
        }
    }

    override suspend fun log(userId: Int, jsonLog: String): Boolean {
        val response = dataSource.log(userId, jsonLog)
        return if (response.isSuccessful) {
            response.body()?.success ?: throw MissingResponseBodyException()
        } else {
            throw ServerErrorException(response.errorBody()?.string() ?: "")
        }
    }

    override suspend fun fetchCurrentSessionKey(): String {
        return dataStore.data.map { preference ->
            preference[stringPreferencesKey(PreferencesKey.SESSION_KEY)] ?: ""
        }.firstOrNull() ?: ""
    }
}
