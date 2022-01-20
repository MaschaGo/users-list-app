package com.maschago.githubusersapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.ui.theme.Blue300
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoilApi
@ExperimentalCoroutinesApi
@Composable
fun UserView(
    user: User,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                val painter =
                    rememberImagePainter(data = user.avatarUrl,
                        builder = {
                            transformations(
                                CircleCropTransformation()
                            )
                        })

                Image(
                    painter = painter,
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .width(200.dp)
                        .height(200.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = user.login,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    color = Blue300,
                    style = MaterialTheme.typography.h3
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                ) {
                    Text(
                        text = "Name: ${user.name}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h4
                    )
                }

                Text(
                    text = "Public repos: ${user.publicRepos}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                val rank = user.followers.toString()
                Text(
                    text = "Followers: $rank",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Created: ${user.created_at} ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = "Updated: ${user.updated_at} ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}