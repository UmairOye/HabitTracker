package com.ub.habittracker.domain.repository

import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.models.DateAndDayModel

interface UserRepository {
    suspend fun insertUser(entity: UserEntity)
    suspend fun isUserExists(email: String, password: String): Boolean
    suspend fun getUsernameByEmail(email: String): String?
    fun makeDateAndDayList(): List<DateAndDayModel>
}