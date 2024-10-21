package com.demo.app.data.core.dto

import com.demo.app.domain.core.model.Session
import com.google.gson.annotations.SerializedName

data class SessionResultDto(
    val success: Boolean,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("session_id")
    val sessionId: Int,
    val token: String?
) {
    fun toDomainModel(): Session = Session(
        userId = userId,
        sessionId = sessionId,
        accessToken = token ?: ""
    )
}
