package com.demo.app.data.core.repository

import com.demo.app.data.core.datasource.AuthRemoteDataSourceImpl
import com.demo.app.domain.core.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSourceImpl
): AuthRepository {
    override suspend fun login(username: String, password: String): Boolean {
        val response = dataSource.login(username, password)
        return response.isSuccessful // TODO: update response parsing
    }

    override suspend fun signUp(username: String, password: String): Boolean {
        val response = dataSource.signup(username, password)
        return response.isSuccessful // TODO: update response parsing
    }

    override suspend fun logout(userId: Int): Boolean {
        val response = dataSource.logout(userId)
        return response.isSuccessful // TODO: update response parsing
    }

    override suspend fun session(sessionId: Int): Boolean {
        val response = dataSource.session(sessionId)
        return response.isSuccessful // TODO: update response parsing
    }

    override suspend fun logs(userId: Int): Boolean {
        val response = dataSource.logs(userId)
        return response.isSuccessful // TODO: update response parsing
    }

    override suspend fun log(userId: Int, jsonLog: String): Boolean {
        val response = dataSource.log(userId, jsonLog)
        return response.isSuccessful // TODO: update response parsing
    }

}
