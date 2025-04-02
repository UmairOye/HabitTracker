package com.ub.habittracker.ui.navigation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ub.habittracker.ui.theme.screens.credentialsScreen.login.LoginScreen
import com.ub.habittracker.ui.theme.screens.credentialsScreen.signup.SignUpScreen


@Composable
fun AppNavHost(
    navController: NavHostController
) {


    NavHost(navController = navController, startDestination = NavigationItems.LOGIN.route) {
        composable(route = NavigationItems.LOGIN.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )

            }) {

            LoginScreen(
                onBackPressed = {},
                onSignInClicked = {route -> navController.navigate(route)},
                onSignUpClicked = {route -> navController.navigate(route)}
            )
        }


        composable(route = NavigationItems.SIGNUP.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(700)
                )

            }) {
            SignUpScreen()
        }
    }
}