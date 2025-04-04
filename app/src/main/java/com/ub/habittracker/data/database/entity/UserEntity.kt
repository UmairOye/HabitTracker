package com.ub.habittracker.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val email: String,
    val password: String
)
