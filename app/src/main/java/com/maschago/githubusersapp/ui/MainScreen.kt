package com.maschago.githubusersapp.ui

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.base.UIRepo
import com.maschago.githubusersapp.navigaton.Screen
import com.maschago.githubusersapp.navigaton.navHostController
import com.maschago.githubusersapp.ui.component.ConnectivityStatus
import com.maschago.githubusersapp.ui.component.ErrorView
import com.maschago.githubusersapp.ui.theme.Blue300
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber
import javax.inject.Inject

class MainScreen @Inject constructor() : UIRepo<MainViewModel> {

    @ExperimentalCoroutinesApi
    @ExperimentalAnimationApi
    @Composable
    override fun ScreenComposition(viewModel: MainViewModel, extras: Bundle?) {

        Scaffold(topBar = {
            TopAppBar(
                title = { Text("GitHub Users App") },
                backgroundColor = Blue300
            )
        }, content = {

            var isSynced by rememberSaveable(key = "notesSyncedInitially") { mutableStateOf(false) }

            val users = viewModel.users.collectAsState(UIState.loading()).value
            val syncState = viewModel.syncState.collectAsState(UIState.loading()).value

            isSynced = isSynced || syncState.isSuccess

            val isRefreshing = users.isLoading or syncState.isLoading


//            SwipeRefresh(
//                state = rememberSwipeRefreshState(isRefreshing),
//                onRefresh = {
//                    isSynced = false
//                    viewModel.syncNotes()
//                }
//            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConnectivityStatus()
                val navController = navHostController()
                NavHost(navController, startDestination = Screen.Users.route, route = NAV_HOST_ROUTE) {
                    composable(Screen.Users.route) {
                        MainContent(navController = navController, users)
                    }
                    composable(
                        Screen.UsersDetail.route,
                        arguments = listOf(
                            navArgument(Screen.UsersDetail.ARG_USER_ID) { type = NavType.StringType }
                        )
                    ) {
                        val userId = requireNotNull(it.arguments?.getString(Screen.UsersDetail.ARG_USER_ID))
                        DetailsScreen(navController, viewModel = hiltViewModel())
                    }
                }
            }
        })

    }

    @Composable
    fun MainContent(navController: NavHostController, users: UIState<List<User>>, ) {
        Greeting("Android")

        when (users) {
            is UIState.Loading -> {
                Text("Loading")
            }
            is UIState.Success -> {
                Timber.i("User size ${users.data.size}")
                UsersList( users.data) { user ->
                    navController.navigate(Screen.UsersDetail.route(user.login))
                }
            }
            is UIState.Failed -> {
                ErrorView(users.message)
            }
        }
//        val users = viewModel.usersList.value
//        val error = viewModel.errorMessage.value


//
//        if (users.isEmpty() && error.isNotEmpty()) {
//            ErrorView(viewModel, error)
//        } else if(users.isEmpty() && error.isEmpty()){
//            ErrorView(viewModel, "Something went wrong")
//        }
//        else {
//            UsersList(viewModel, users) { user ->
//                navController.navigate(Screen.UsersDetail.route(user.login))
//            }
//        }


//        when (val state = viewModel.state.collectAsState().value) {
//           is UIState.Loading -> {
//                Text("Loading")
//            }
//            is UIState.Success -> {
//                UsersList(viewModel, users.) { user ->
//                    navController.navigate(Screen.UsersDetail.route(user.login))
//                }
//            }
//            is UIState.Failed ->{
//                ErrorView(viewModel, state.message)
//            }
//        }


    }

    @Composable
    private fun UsersList(users: List<User>, onClick: (User) -> Unit) {

        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(
                items = users,
                itemContent = { user ->
                    UserCard(
                        user = user,
                        onUserClick = { onClick(user) }
                    )
                }
            )
        }
    }


    @Composable
    fun UserCard(user: User, onUserClick: () -> Unit) {
        Card(
            shape = RoundedCornerShape(4.dp),
            backgroundColor = MaterialTheme.colors.surface,
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onUserClick() },
            elevation = 0.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.h5,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = user.type,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    lineHeight = 24.sp,
                    maxLines = 2
                )
            }
        }
    }


    @Composable
    fun UserRow(viewModel: MainViewModel, user: User, onClick: (User) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    onClick(user)
                }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp), verticalAlignment = Alignment.CenterVertically
                ) {
//                    Image(
//                        modifier = Modifier
//                            .size(50.dp)
//                            .padding(4.dp),
//                        contentDescription = country.alpha3Code, painter = rememberImagePainter(
//                            data = country.flags.png,
//                            builder = {
//                                allowHardware(false)
//                            })
//                    )
                    Text(" ${user.login}", modifier = Modifier.padding(4.dp))
                }
                Divider(color = Color.LightGray)
            }
        }
    }


    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name! You are here")
    }

    @Composable
    fun ItemsBoxWitchTitle(shape: Shape, title: String, dataList: List<User>) {
        val textColor = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Box(
                modifier = Modifier
                    .clip(shape)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
                        shape = shape
                    )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(title, fontSize = 14.sp, color = textColor)
                    for (data in dataList) {
                        Text("• ${data.id}", fontSize = 16.sp, color = textColor)
                    }
                }
            }
        }
    }

    companion object {
        const val NAV_HOST_ROUTE = "main-route"
    }
}
