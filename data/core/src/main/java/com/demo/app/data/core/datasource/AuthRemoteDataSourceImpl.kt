package com.demo.app.data.core.datasource


import com.demo.app.data.core.api.AuthApi
import com.demo.app.data.core.dto.LogResultDto
import com.demo.app.data.core.dto.LogsResultDto
import com.demo.app.data.core.dto.RequestResultDto
import com.demo.app.data.core.dto.SessionResultDto
import com.demo.app.data.core.dto.UserResultDto
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: AuthApi,
    private val gson: Gson
) {

    suspend fun login(
        username: String,
        password: String
    ): Response<SessionResultDto> {
        return api.login(
            gson.toJsonTree(
                linkedMapOf<String, Any?>().apply {
                    put("username", username)
                    put("password", password)
                }
            ).asJsonObject
        )
    }

    suspend fun signup(
        username: String,
        password: String
    ): Response<UserResultDto> {
        return api.signup(
            gson.toJsonTree(
                linkedMapOf<String, Any?>().apply {
                    put("username", username)
                    put("password", password)
                }
            ).asJsonObject
        )
    }

    suspend fun logout(
        userId: Int
    ): Response<RequestResultDto> {
        return api.logout(
            gson.toJsonTree(
                linkedMapOf<String, Any?>().apply {
                    put("user_id", userId)
                }
            ).asJsonObject
        )
    }

    suspend fun session(
        sessionId: Int
    ): Response<SessionResultDto> {
        return api.session(sessionId = sessionId)
    }

    suspend fun logs(
        userId: Int
    ): Response<LogsResultDto> {
        return api.logs(userId = userId)
    }

    suspend fun log(
        userId: Int,
        jsonLog: String
    ): Response<LogResultDto> {
        return api.log(
            gson.toJsonTree(
                linkedMapOf<String, Any?>().apply {
                    put("user_id", userId)
                    put("log", jsonLog)
                }
            ).asJsonObject
        )
    }
}
