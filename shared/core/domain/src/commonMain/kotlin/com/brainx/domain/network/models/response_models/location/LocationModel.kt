package com.brainx.domain.network.models.response_models.location

import com.brainx.utils_extensions.constants.ExtConstants.StringConstants.EMPTY
import kotlinx.serialization.Serializable

@Serializable
data class LocationModel(

    val coordinates: CoordinatesModel = CoordinatesModel(),
    val placeId: String? = EMPTY,
    val displayName: String = EMPTY,
    val city: String = EMPTY,
    val country: String = EMPTY,
    val countryCode: String = EMPTY,
    val postalCode: String = EMPTY,
    val state: String = EMPTY

)
