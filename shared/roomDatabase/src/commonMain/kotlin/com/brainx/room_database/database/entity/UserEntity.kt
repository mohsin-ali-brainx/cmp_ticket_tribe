package com.brainx.room_database.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val firstName: String = "",
    val lastName: String = "",
    val avatar: String = "",
    val shippingDetail: String = "",
    val interests: String = "",
    val username: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val countryCode: String = "",
    val authProvider: String = "",
    val emailVarified: Boolean = false,
    val passwordSet: Boolean = false,
    val skipSetPassword: Boolean = false,
    val notifications: Boolean = false,
    val showFavoritesToFollowers: Boolean = false,
    val gender: String = "",
    val locationLatitude: Double = 0.0,
    val locationLongitude: Double = 0.0,
    val locationPlaceId: String = "",
    val locationDisplayName: String = "",
    val locationCity: String = "",
    val locationCountry: String = "",
    val locationCountryCode: String = "",
    val locationPostalCode: String = "",
    val locationState: String = "",
)
