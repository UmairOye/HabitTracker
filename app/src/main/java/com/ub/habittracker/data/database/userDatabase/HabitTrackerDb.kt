package com.ub.habittracker.data.database.userDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ub.habittracker.data.database.dao.UserDao
import com.ub.habittracker.data.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class HabitTrackerDb: RoomDatabase() {
    abstract fun userDao():  UserDao
}