package com.brainx.datasource.network.models.response_models.location

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(

    @SerialName("coordinates") var coordinates: CoordinatesResponse? = CoordinatesResponse(),
    @SerialName("placeId") var placeId: String? = null,
    @SerialName("displayName") var displayName: String? = null,
    @SerialName("city") var city: String? = null,
    @SerialName("country") var country: String? = null,
    @SerialName("countryCode") var countryCode: String? = null,
    @SerialName("postalCode") var postalCode: String? = null,
    @SerialName("state") var state: String? = null

)
