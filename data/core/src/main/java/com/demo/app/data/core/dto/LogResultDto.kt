package com.demo.app.data.core.dto

import com.google.gson.annotations.SerializedName

data class LogResultDto(
    val success: Boolean,
    @SerializedName("log_id")
    val logId: Int
)
