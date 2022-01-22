package com.maschago.githubusersapp.ui

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.maschago.githubusersapp.base.UIRepo
import com.maschago.githubusersapp.navigaton.Screen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class ScreenUIRepo @Inject constructor() : UIRepo<MainViewModel> {

    @ExperimentalFoundationApi
    @ExperimentalCoilApi
    @ExperimentalCoroutinesApi
    @ExperimentalAnimationApi
    @Composable
    override fun ScreenComposition(viewModel: MainViewModel, extras: Bundle?) {
        val navController = rememberNavController()

        NavHost(navController, startDestination = Screen.Users.route, route = NAV_HOST_ROUTE) {
            composable(Screen.Users.route) {
                MainScreen(navController = navController, viewModel)
            }
            composable(
                Screen.UsersDetail.route,
                arguments = listOf(
                    navArgument(Screen.UsersDetail.ARG_USER_ID) { type = NavType.StringType }
                )
            ) {
                val factory = HiltViewModelFactory(LocalContext.current, it)
                val viewModel: DetailViewModel = viewModel(key = "DetailViewModel", factory = factory)
                DetailsScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
        }
    }

    companion object {
        const val NAV_HOST_ROUTE = "main-route"
    }
}
