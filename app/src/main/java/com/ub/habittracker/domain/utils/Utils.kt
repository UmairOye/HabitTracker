package com.ub.habittracker.domain.utils

import android.util.Log

object Utils {
    fun logCall(message: String, tag: String = "HabitTrackerApp"){
        Log.d(tag, message)
    }
}