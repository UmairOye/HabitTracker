package com.ub.habittracker.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ub.habittracker.data.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertUser(entity: UserEntity)

    @Query("SELECT COUNT(*) FROM tbl_users WHERE email = :email AND password = :password")
    fun isUserExists(email: String, password: String): Int

}