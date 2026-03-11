package com.brainx.domain.network.models.response_models.location

import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesModel(
     val latitude: Double = 0.0,
     val longitude: Double = 0.0
)