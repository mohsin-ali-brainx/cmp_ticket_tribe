package com.brainx.domain.network.dto

import com.brainx.utils_extensions.getPlatform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class LoginDto(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("firebaseToken") val firebaseToken: String="",
    @SerialName("platform") val platform: String = getPlatform().platform
)
