package com.maschago.githubusersapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.ui.theme.Blue300

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
            Row() {
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
                        .width(48.dp)
                        .height(48.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier.
                        padding(16.dp, 0.dp)
                        .align(CenterVertically),
                    text = user.login,
                    style = MaterialTheme.typography.h5,
                    color = Blue300,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}