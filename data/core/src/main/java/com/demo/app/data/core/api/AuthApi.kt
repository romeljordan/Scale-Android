package com.demo.app.data.core.api

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
    ): Response<Boolean>

    @POST("/signup")
    suspend fun signup(
        @Body body: JsonObject
    ): Response<Boolean>

    @POST("/logout")
    suspend fun logout(
        @Body body: JsonObject
    ): Response<Boolean>

    @POST("/log")
    suspend fun log(
        @Body body: JsonObject
    ): Response<Boolean>

    @GET("/logs/{user_id}")
    suspend fun logs(
        @Path("user_id") userId: Int
    )

    @GET("/session/{session_id}")
    suspend fun session(
        @Path("session_id") sessionId: Int
    )
}
