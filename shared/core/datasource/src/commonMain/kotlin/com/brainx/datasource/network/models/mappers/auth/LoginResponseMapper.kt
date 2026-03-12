package com.brainx.datasource.network.models.mappers.auth

import com.brainx.datasource.network.models.response_models.auth.LoginResponse
import com.brainx.datasource.network.models.response_models.location.LocationResponse
import com.brainx.datasource.network.models.response_models.user.UserResponse
import com.brainx.domain.network.models.response_models.auth.LoginModel
import com.brainx.domain.network.models.response_models.auth.Suggestions
import com.brainx.domain.network.models.response_models.location.CoordinatesModel
import com.brainx.domain.network.models.response_models.location.LocationModel
import com.brainx.domain.network.models.response_models.user.Interest
import com.brainx.domain.network.models.response_models.user.UserModel
import com.brainx.domain.utils.enums.AuthProvider
import com.brainx.domain.utils.enums.Gender
import com.brainx.utils_extensions.extensions.orEmpty
import com.brainx.utils_extensions.extensions.orFalse
import com.brainx.utils_extensions.extensions.orZero

fun LoginResponse.toLoginModel() = LoginModel(
    access = access ,
    refresh = refresh ,
    user = user?.toUserModel(),
    suggestions = suggestions?.map { Suggestions(it) }
)

fun UserResponse.toUserModel() = UserModel(
    id = id.orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    email = email.orEmpty(),
    username = username.orEmpty(),
    avatar = avatar.orEmpty(),
    shippingDetail = shippingDetail.orEmpty(),
    interests = interests.map { Interest(it) },
    emailVarified = emailVarified.orFalse(),
    passwordSet = passwordSet.orFalse(),
    skipSetPassword = skipSetPassword.orFalse(),
    notifications = notifications.orFalse(),
    showFavoritesToFollowers = showFavoritesToFollowers.orFalse(),
    gender = Gender.entries.find { it.key==gender } as Gender,
    location = location.toLocationModel(),
    phoneNumber = phoneNumber.orEmpty(),
    countryCode = countryCode.orEmpty(),
    authProvider = AuthProvider.entries.find { it.key==authProvider } as AuthProvider,

)

fun LocationResponse?.toLocationModel() = LocationModel(
    placeId = this?.placeId.orEmpty(),
    displayName = this?.displayName.orEmpty(),
    city = this?.city.orEmpty(),
    country = this?.country.orEmpty(),
    countryCode = this?.countryCode.orEmpty(),
    postalCode = this?.postalCode.orEmpty(),
    state = this?.state.orEmpty(),
    coordinates = this?.coordinates?.let { CoordinatesModel(it.latitude.orZero(), it.longitude.orZero()) } as CoordinatesModel
)