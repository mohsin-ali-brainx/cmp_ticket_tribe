package com.brainx.datasource.network.models.response_models.user

import com.brainx.datasource.network.models.response_models.location.LocationResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("avatar") var avatar: String? = null,
    @SerialName("shippingDetail") var shippingDetail: String? = null,
    @SerialName("_id") var id: String? = null,
    @SerialName("firstName") var firstName: String? = null,
    @SerialName("lastName") var lastName: String? = null,
    @SerialName("interests") var interests: ArrayList<String> = arrayListOf(),
    @SerialName("username") var username: String? = null,
    @SerialName("email") var email: String? = null,
    @SerialName("phoneNumber") var phoneNumber: String? = null,
    @SerialName("countryCode") var countryCode: String? = null,
    @SerialName("authProvider") var authProvider: String? = null,
    @SerialName("emailVarified") var emailVarified: Boolean? = null,
    @SerialName("passwordSet") var passwordSet: Boolean? = null,
    @SerialName("skipSetPassword") var skipSetPassword: Boolean? = null,
    @SerialName("notifications") var notifications: Boolean? = null,
    @SerialName("showFavoritesToFollowers") var showFavoritesToFollowers: Boolean? = null,
    @SerialName("createdAt") var createdAt: String? = null,
    @SerialName("updatedAt") var updatedAt: String? = null,
    @SerialName("__v") var version: Int? = null,
    @SerialName("gender") var gender: String? = null,
    @SerialName("location") var location: LocationResponse? = LocationResponse()
)
