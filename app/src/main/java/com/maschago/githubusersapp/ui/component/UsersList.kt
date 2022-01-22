package com.maschago.githubusersapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun UsersList(viewModel: MainViewModel, users: List<User>, onClick: (User) -> Unit) {
    val lastIndex = users.lastIndex
    val listState = rememberLazyListState()
    LazyColumn(contentPadding = PaddingValues(16.dp), state = listState) {
        items(
            items = users,
            itemContent = { user ->
                if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == lastIndex) {
                    viewModel.loadNextPage()
                }
                UserCard(
                    user = user,
                    onUserClick = { onClick(user) }
                )
            }
        )
    }

}



