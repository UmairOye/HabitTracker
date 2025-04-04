package com.ub.habittracker.ui.navigation

sealed class NavigationItems(val route: String) {
    object LOGIN: NavigationItems(Screens.LOGIN.name)
    object SIGNUP: NavigationItems(Screens.SIGNUP.name)
    object HOME : NavigationItems(Screens.HOME.name)
}