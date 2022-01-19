package com.maschago.githubusersapp.navigaton

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi


const val NAV_HOST_ROUTE = "main-route"

@Composable
fun navHostController(): NavHostController = rememberNavController()


@ExperimentalAnimationApi
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Composable
fun AppNavigation() {
//    val navController = navHostController()
//
//    NavHost(navController, startDestination = Screen.Users.route, route = NAV_HOST_ROUTE) {
//
//        composable(Screen.Users.route) {
//            MainScreen()
//        }
//        composable(
//            Screen.UsersDetail.route,
//            arguments = listOf(
//                navArgument(Screen.UsersDetail.ARG_USER_ID) { type = NavType.StringType }
//            )
//        ) {
//            val userId = requireNotNull(it.arguments?.getString(Screen.UsersDetail.ARG_USER_ID))
//            DetailsScreen()
//        }
//
//    }
}

