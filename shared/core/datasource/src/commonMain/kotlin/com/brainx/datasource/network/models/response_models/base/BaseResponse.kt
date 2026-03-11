package com.brainx.datasource.network.models.response_models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class BaseResponse<T>(
    @SerialName("data")
    var data : T? = null,
    @SerialName("message")
    var message : String? = null
)
