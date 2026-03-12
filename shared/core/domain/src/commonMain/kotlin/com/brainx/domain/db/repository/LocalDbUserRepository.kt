package com.brainx.domain.db.repository

import com.brainx.domain.network.models.response_models.user.UserModel
import kotlinx.coroutines.flow.Flow

interface LocalDbUserRepository {
    suspend fun insertUser(user: UserModel)
    suspend fun updateUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
    suspend fun deleteAllUsers()
    fun getUserById(userId: String): Flow<UserModel?>
    fun getCurrentUser(): Flow<UserModel?>

}