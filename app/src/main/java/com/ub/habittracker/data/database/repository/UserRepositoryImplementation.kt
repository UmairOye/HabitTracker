package com.ub.habittracker.data.database.repository

import com.ub.habittracker.data.database.dao.UserDao
import com.ub.habittracker.data.database.entity.UserEntity
import com.ub.habittracker.domain.models.DateAndDayModel
import com.ub.habittracker.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImplementation @Inject constructor(private val userDao: UserDao): UserRepository {
    override suspend fun insertUser(entity: UserEntity) = withContext(Dispatchers.IO){
        userDao.insertUser(entity)
    }

    override suspend fun isUserExists(email: String, password: String): Boolean  = withContext(Dispatchers.IO){
        return@withContext userDao.isUserExists(email, password) > 0
    }

    override suspend fun getUsernameByEmail(email: String): String? = withContext(Dispatchers.IO) {
        return@withContext userDao.getUsernameByEmail(email)
    }

    override fun makeDateAndDayList(): List<DateAndDayModel> {
        val list = mutableListOf<DateAndDayModel>().apply {
            add(DateAndDayModel("Mon", "05"))
            add(DateAndDayModel("Tue", "06"))
            add(DateAndDayModel("Wed", "07"))
            add(DateAndDayModel("Thu", "08"))
            add(DateAndDayModel("Fri", "09"))
            add(DateAndDayModel("Sat", "10"))
            add(DateAndDayModel("Sun", "11"))
            add(DateAndDayModel("Mon", "12"))
            add(DateAndDayModel("Fri", "13"))
            add(DateAndDayModel("Sat", "14"))
        }

        return list
    }


}