package com.brainx.domain.network.models.response_models.auth

import com.brainx.domain.network.models.response_models.user.UserModel
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class LoginResponseModel(
    val access  : String? = null,
    val refresh : String? = null,
    val suggestions : List<Suggestions>? = emptyList(),
    val user    : UserModel?   = UserModel()
)

@JvmInline
@Serializable
value class Suggestions(val suggestions: String)