package com.ub.habittracker.data.database.repository

import com.ub.habittracker.data.database.dao.UserDao
import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val userDao: UserDao): UserRepository {
    override suspend fun insertUser(entity: UserEntity) = withContext(Dispatchers.IO){
        userDao.insertUser(entity)
    }

    override suspend fun isUserExist(userName: String, email: String): UserEntity = withContext(Dispatchers.IO){
        return@withContext userDao.isUserExist(userName, email)
    }
}