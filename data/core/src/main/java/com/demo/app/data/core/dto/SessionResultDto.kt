package com.demo.app.data.core.dto

data class SessionResultDto(
    val success: Boolean,
    val userId: Int,
    val sessionId: Int
)
