package com.maschago.githubusersapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.maschago.githubusersapp.navigaton.Screen
import com.maschago.githubusersapp.ui.component.ConnectivityStatus
import com.maschago.githubusersapp.ui.component.ErrorView
import com.maschago.githubusersapp.ui.component.UsersList
import com.maschago.githubusersapp.ui.theme.Blue300
import com.maschago.githubusersapp.utils.ConnectionState
import com.maschago.githubusersapp.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("GitHub Users App", color = Color.White) },
            backgroundColor = Blue300
        )

    }, content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConnectivityStatus()

            val connection by connectivityState()
            val isConnected = connection === ConnectionState.Available

            if (isConnected) {
                val users = viewModel.usersList.value
                val error = viewModel.errorMessage.value

                if (users.isEmpty() && error.isNotEmpty()) {
                    ErrorView(error) {
                        viewModel.retryLoading()
                    }
                } else if (users.isEmpty() && error.isEmpty()) {
                    viewModel.retryLoading()
                } else {
                    UsersList(users) { user ->
                        navController.navigate(Screen.UsersDetail.route(user.login))
                    }
                }
            } else {
                ErrorView("Please check your internet connection and try again.") {
                    viewModel.retryLoading()
                }
            }
        }
    })

}
