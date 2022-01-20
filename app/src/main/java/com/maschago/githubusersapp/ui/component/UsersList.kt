package com.maschago.githubusersapp.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.maschago.githubusersapp.User


@Composable
fun UsersList(users: List<User>, onClick: (User) -> Unit) {
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