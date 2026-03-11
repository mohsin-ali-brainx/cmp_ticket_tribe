package com.brainx.domain.network.models.response_models.user

import com.brainx.domain.network.models.response_models.location.LocationModel
import com.brainx.domain.utils.enums.AuthProvider
import com.brainx.domain.utils.enums.Gender
import com.brainx.utils_extensions.constants.ExtConstants.StringConstants.EMPTY
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
data class UserModel(
    val id: String = EMPTY,
    val firstName: String = EMPTY,
    val lastName: String = EMPTY,
    val avatar: String = EMPTY,
    val shippingDetail: String = EMPTY,
    val interests: List<Interest> = listOf(),
    val username: String = EMPTY,
    val email: String = EMPTY,
    val phoneNumber: String = EMPTY,
    val countryCode: String = EMPTY,
    val authProvider: AuthProvider = AuthProvider.DEFAULT,
    val emailVarified: Boolean = false,
    val passwordSet: Boolean = false,
    val skipSetPassword: Boolean = false,
    val notifications: Boolean = false,
    val showFavoritesToFollowers: Boolean = false,
    val gender: Gender = Gender.MALE,
    val location: LocationModel = LocationModel()
)

@JvmInline
@Serializable
value class Interest(val interest: String)
