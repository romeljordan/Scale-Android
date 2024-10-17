package com.demo.app.domain.core.repository

import com.demo.app.domain.core.model.Session
import com.demo.app.domain.core.model.WeatherLog

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String
    ): Session

    suspend fun signUp(
        username: String,
        password: String
    ): Boolean

    suspend fun logout(
        userId: Int
    ): Boolean

    suspend fun session(
        sessionId: Int
    ): Session

    suspend fun logs(
        userId: Int
    ): List<WeatherLog>

    suspend fun log(
        userId: Int,
        jsonLog: String
    ): Boolean

    suspend fun fetchCurrentSessionKey(): String
}
