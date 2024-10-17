package com.demo.app.domain.core.usecase

import com.demo.app.domain.core.repository.AuthRepository
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
): AuthUseCase {
    override suspend fun login(username: String, password: String): Result<Int> {
        return try {
            Result.success(repository.login(username, password))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun signUp(username: String, password: String): Result<Boolean> {
        return try {
            Result.success(repository.signUp(username, password))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun logout(userId: Int): Result<Boolean> {
        return try {
            Result.success(repository.logout(userId))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun session(sessionId: Int): Result<Boolean> {
        return try {
            Result.success(repository.session(sessionId))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun logs(userId: Int): Result<List<String>> {
        return try {
            Result.success(repository.logs(userId))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun log(userId: Int, jsonLog: String): Result<Boolean> {
        return try {
            Result.success(repository.log(userId, jsonLog))
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}
