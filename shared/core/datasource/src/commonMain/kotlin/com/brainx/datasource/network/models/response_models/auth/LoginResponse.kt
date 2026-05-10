package com.brainx.datasource.network.models.response_models.auth

import com.brainx.datasource.network.models.response_models.user.UserResponse
import com.brainx.domain.network.models.response_models.auth.Suggestions
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access"  ) var access  : String? = null,
    @SerialName("refresh" ) var refresh : String? = null,
    @SerialName("suggestions" ) val suggestions : List<String>? = emptyList(),
    @SerialName("user"    ) var user    : UserResponse?   = UserResponse()
)
