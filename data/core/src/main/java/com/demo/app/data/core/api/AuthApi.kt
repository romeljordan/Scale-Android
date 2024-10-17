package com.demo.app.data.core.api

import com.demo.app.data.core.dto.LogResultDto
import com.demo.app.data.core.dto.LogsResultDto
import com.demo.app.data.core.dto.RequestResultDto
import com.demo.app.data.core.dto.UserResultDto
import com.demo.app.data.core.dto.SessionResultDto
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("/login")
    suspend fun login(
        @Body body: JsonObject
    ): Response<SessionResultDto>

    @POST("/signup")
    suspend fun signup(
        @Body body: JsonObject
    ): Response<UserResultDto>

    @POST("/logout")
    suspend fun logout(
        @Body body: JsonObject
    ): Response<RequestResultDto>

    @POST("/log")
    suspend fun log(
        @Body body: JsonObject
    ): Response<LogResultDto>

    @GET("/logs/{user_id}")
    suspend fun logs(
        @Path("user_id") userId: Int
    ): Response<LogsResultDto>

    @GET("/session/{session_id}")
    suspend fun session(
        @Path("session_id") sessionId: Int
    ): Response<SessionResultDto>
}
