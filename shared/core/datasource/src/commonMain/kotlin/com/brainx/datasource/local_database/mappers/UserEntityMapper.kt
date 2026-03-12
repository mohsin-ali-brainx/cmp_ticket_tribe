package com.brainx.datasource.local_database.mappers

import com.brainx.domain.network.models.response_models.location.CoordinatesModel
import com.brainx.domain.network.models.response_models.location.LocationModel
import com.brainx.domain.network.models.response_models.user.Interest
import com.brainx.domain.network.models.response_models.user.UserModel
import com.brainx.domain.utils.enums.AuthProvider
import com.brainx.domain.utils.enums.Gender
import com.brainx.room_database.database.entity.UserEntity

private const val INTERESTS_DELIMITER = ","

fun UserEntity.toUserModel() = UserModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
    shippingDetail = shippingDetail,
    interests = interests
        .takeIf { it.isNotBlank() }
        ?.split(INTERESTS_DELIMITER)
        ?.map { Interest(it) }
        .orEmpty(),
    username = username,
    email = email,
    phoneNumber = phoneNumber,
    countryCode = countryCode,
    authProvider = AuthProvider.entries.firstOrNull { it.key == authProvider } ?: AuthProvider.DEFAULT,
    emailVarified = emailVarified,
    passwordSet = passwordSet,
    skipSetPassword = skipSetPassword,
    notifications = notifications,
    showFavoritesToFollowers = showFavoritesToFollowers,
    gender = Gender.entries.firstOrNull { it.key == gender } ?: Gender.MALE,
    location = LocationModel(
        coordinates = CoordinatesModel(
            latitude = locationLatitude,
            longitude = locationLongitude
        ),
        placeId = locationPlaceId,
        displayName = locationDisplayName,
        city = locationCity,
        country = locationCountry,
        countryCode = locationCountryCode,
        postalCode = locationPostalCode,
        state = locationState
    )
)

fun UserModel.toUserEntity() = UserEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
    shippingDetail = shippingDetail,
    interests = interests.joinToString(INTERESTS_DELIMITER) { it.interest },
    username = username,
    email = email,
    phoneNumber = phoneNumber,
    countryCode = countryCode,
    authProvider = authProvider.key,
    emailVarified = emailVarified,
    passwordSet = passwordSet,
    skipSetPassword = skipSetPassword,
    notifications = notifications,
    showFavoritesToFollowers = showFavoritesToFollowers,
    gender = gender.key,
    locationLatitude = location.coordinates.latitude,
    locationLongitude = location.coordinates.longitude,
    locationPlaceId = location.placeId.orEmpty(),
    locationDisplayName = location.displayName,
    locationCity = location.city,
    locationCountry = location.country,
    locationCountryCode = location.countryCode,
    locationPostalCode = location.postalCode,
    locationState = location.state,
)
