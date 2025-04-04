package com.ub.habittracker.ui.habitApp

import android.app.Application
import com.ub.habittracker.data.local.SharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabitTrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}