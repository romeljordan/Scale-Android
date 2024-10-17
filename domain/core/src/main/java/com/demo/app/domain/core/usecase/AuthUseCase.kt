package com.demo.app.domain.core.usecase

import com.demo.app.domain.core.model.Session

interface AuthUseCase {
    suspend fun login(
        username: String,
        password: String
    ): Result<Session>

    suspend fun signUp(
        username: String,
        password: String
    ): Result<Boolean>

    suspend fun logout(
        userId: Int
    ): Result<Boolean>

    suspend fun session(
        sessionId: Int
    ): Result<Session>

    suspend fun logs(
        userId: Int
    ): Result<List<String>>

    suspend fun log(
        userId: Int,
        jsonLog: String
    ): Result<Boolean>
}