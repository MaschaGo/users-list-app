package com.maschago.githubusersapp.navigaton

sealed class Screen(val route: String, val screenName: String) {
    object Users : Screen("users", "Users")
    object UsersDetail : Screen("user/{userId}", "User details") {
        fun route(userId: String) = "user/$userId"

        const val ARG_USER_ID: String = "userId"
    }
}