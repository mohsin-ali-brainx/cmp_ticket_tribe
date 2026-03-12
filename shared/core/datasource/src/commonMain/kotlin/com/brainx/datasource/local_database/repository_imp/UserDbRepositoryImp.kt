package com.brainx.datasource.local_database.repository_imp

import com.brainx.datasource.local_database.mappers.toUserEntity
import com.brainx.datasource.local_database.mappers.toUserModel
import com.brainx.domain.db.repository.LocalDbUserRepository
import com.brainx.domain.network.models.response_models.user.UserModel
import com.brainx.room_database.repository.UserDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDbRepositoryImp(
    val userDbRepository: UserDbRepository
): LocalDbUserRepository {
    override suspend fun insertUser(user: UserModel) {
        userDbRepository.insertUser(user.toUserEntity())
    }

    override suspend fun updateUser(user: UserModel) {
        userDbRepository.updateUser(user.toUserEntity())

    }

    override suspend fun deleteUser(user: UserModel) {
        userDbRepository.deleteUser(user.toUserEntity())

    }

    override suspend fun deleteAllUsers() {
        userDbRepository.deleteAllUsers()
    }

    override fun getUserById(userId: String): Flow<UserModel?> = flow {
        userDbRepository.getUserById(userId).collect {
            emit(it?.toUserModel())
        }
    }

    override fun getCurrentUser(): Flow<UserModel?> = flow {
        userDbRepository.getCurrentUser().collect {
            emit(it?.toUserModel())
        }
    }
}