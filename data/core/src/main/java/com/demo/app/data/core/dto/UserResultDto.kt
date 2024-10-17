package com.demo.app.data.core.dto

import com.google.gson.annotations.SerializedName

data class UserResultDto(
    val success: Boolean,
    @SerializedName("user_id")
    val userId: Int
)
