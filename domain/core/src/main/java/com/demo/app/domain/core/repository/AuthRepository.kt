package com.demo.app.domain.core.repository

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String
    ): Int

    suspend fun signUp(
        username: String,
        password: String
    ): Boolean

    suspend fun logout(
        userId: Int
    ): Boolean

    suspend fun session(
        sessionId: Int
    ): Boolean

    suspend fun logs(
        userId: Int
    ): List<String>

    suspend fun log(
        userId: Int,
        jsonLog: String
    ): Boolean
}
