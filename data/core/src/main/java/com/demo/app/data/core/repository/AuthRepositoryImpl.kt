package com.demo.app.data.core.repository

import android.util.Log
import com.demo.app.data.core.datasource.AuthRemoteDataSourceImpl
import com.demo.app.domain.core.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthRemoteDataSourceImpl
): AuthRepository {
    override suspend fun login(username: String, password: String): Int {
        val response = dataSource.login(username, password)
        Log.i("QWERTY", "login: $response")
        return if (response.isSuccessful) {
            response.body()?.userId ?: throw Throwable("Missing body")
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
            response.body()?.success ?: throw Throwable("Missing body")
        } else {
            throw Throwable(response.errorBody()?.string())
        }
    }

    override suspend fun session(sessionId: Int): Boolean {
        val response = dataSource.session(sessionId)
        return if (response.isSuccessful) {
            response.body()?.success ?: throw Throwable("Missing body")
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
