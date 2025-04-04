package com.ub.habittracker.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.ub.habittracker.data.local.SharedPref
import com.ub.habittracker.domain.utils.Constants.REMEMBER_ME
import com.ub.habittracker.ui.navigation.AppNavHost
import com.ub.habittracker.ui.navigation.NavigationItems
import com.ub.habittracker.ui.theme.HabitTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {

                val startRoute = if (SharedPref.getBool(REMEMBER_ME, defaultValue = false)) {
                    NavigationItems.HOME.route
                } else {
                    NavigationItems.LOGIN.route
                }

                AppNavHost(navController = rememberNavController(), startRoute)
            }
        }
    }
}