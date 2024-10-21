package com.demo.app.domain.core.repository

import kotlinx.coroutines.flow.Flow

interface AccessTokenRepository {
    suspend fun saveAccessToken(token: String): Boolean

    suspend fun fetchAccessToken(): Flow<String>
}
