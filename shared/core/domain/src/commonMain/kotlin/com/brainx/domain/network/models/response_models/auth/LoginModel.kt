package com.brainx.domain.network.models.response_models.auth

import com.brainx.domain.network.models.response_models.user.UserModel
import kotlin.jvm.JvmInline


data class LoginModel(
    val access  : String? = null,
    val refresh : String? = null,
    val suggestions : List<Suggestions>? = emptyList(),
    val user    : UserModel?   = UserModel()
)

@JvmInline
value class Suggestions(val suggestions: String)