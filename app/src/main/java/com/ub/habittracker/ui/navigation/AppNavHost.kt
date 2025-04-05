package com.ub.habittracker.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ub.habittracker.ui.theme.screens.credentialsScreen.login.LoginScreen
import com.ub.habittracker.ui.screens.credentialsScreen.signup.SignUpScreen
import com.ub.habittracker.ui.screens.dashboardScreen.home.HomeScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    startRoute: String = NavigationItems.LOGIN.route
) {


    NavHost(navController = navController, startDestination = startRoute) {
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
                onSignInClicked = { route -> navController.navigate(route) },
                onSignUpClicked = { route -> navController.navigate(route) }
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

            SignUpScreen(
                onBackPressed = { navController.popBackStack() },
                onSignUpClicked = { route -> navController.navigate(route) },
                onSignInClicked = { route -> navController.navigate(route) }
            )
        }


        composable(route = NavigationItems.HOME.route+ "/{user_email}",
            arguments = listOf(navArgument("user_email") { type = NavType.StringType }),
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
            }
        ){

            val user_email = it.arguments?.getString("user_email")?: ""

            HomeScreen(
                email = user_email
            )
        }
    }
}