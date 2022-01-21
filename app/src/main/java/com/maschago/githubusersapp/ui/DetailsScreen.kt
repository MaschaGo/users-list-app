package com.maschago.githubusersapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.maschago.githubusersapp.ui.component.ConnectivityStatus
import com.maschago.githubusersapp.ui.component.ErrorView
import com.maschago.githubusersapp.ui.component.UserView
import com.maschago.githubusersapp.ui.theme.Blue300
import com.maschago.githubusersapp.utils.ConnectionState
import com.maschago.githubusersapp.utils.connectivityState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun DetailsScreen(navController: NavHostController, viewModel: DetailViewModel) {


    val user = viewModel.user.value
    val error = viewModel.errorMessage.value

    val userName: String = user?.login ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("GitHub User $userName", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Rounded.ArrowBack, "Back", tint = Color.White)
                    }
                },
                backgroundColor = Blue300
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConnectivityStatus()

                val connection by connectivityState()
                val isConnected = connection === ConnectionState.Available

                if (isConnected) {
                    if (user == null && error.isNotEmpty()) {
                        ErrorView(error) {
                            viewModel.retryLoadingUserDetails()
                        }
                    } else if (user == null && error.isEmpty()) {
                        viewModel.retryLoadingUserDetails()
                    } else {
                        UserView(user = user!!)
                    }
                } else {
                    ErrorView("Please check your internet connection and try again.") {
                        viewModel.retryLoadingUserDetails()
                    }
                }
            }
        }
    )

}
