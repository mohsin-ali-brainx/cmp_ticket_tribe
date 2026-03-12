package com.brainx.room_database.repository

import com.brainx.room_database.database.dao.UserDao
import com.brainx.room_database.database.entity.UserEntity

class UserDbRepository(
    val userDao: UserDao
) {
    suspend fun insertUser(user: UserEntity) {
        userDao.insert(user)
    }
    suspend fun updateUser(user: UserEntity) {
        userDao.update(user)
    }
    suspend fun deleteUser(user: UserEntity) {
        userDao.delete(user)
    }
    suspend fun deleteAllUsers() {
        userDao.deleteAll()
    }

    fun getUserById(userId: String) = userDao.getUserById(userId)

    fun getCurrentUser() = userDao.getCurrentUser()

}