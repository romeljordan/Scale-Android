package com.demo.app.domain.core.usecase

interface AuthUseCase {
    suspend fun login(
        username: String,
        password: String
    ): Result<Int>

    suspend fun signUp(
        username: String,
        password: String
    ): Result<Boolean>

    suspend fun logout(
        userId: Int
    ): Result<Boolean>

    suspend fun session(
        sessionId: Int
    ): Result<Boolean>

    suspend fun logs(
        userId: Int
    ): Result<List<String>>

    suspend fun log(
        userId: Int,
        jsonLog: String
    ): Result<Boolean>
}