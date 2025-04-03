package com.ub.habittracker.domain.repository

import com.ub.habittracker.data.database.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(entity: UserEntity)
    suspend fun isUserExists(email: String, password: String): Boolean
}