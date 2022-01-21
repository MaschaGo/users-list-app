package com.maschago.githubusersapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ErrorView(error: String, onButtonClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Text(error, modifier = Modifier.padding(16.dp)) }
        item {
            Button(
                modifier = Modifier.padding(16.dp, 32.dp, 16.dp, 0.dp),
                onClick = {
                   onButtonClick()
                }
            ) {
                Text("Retry".uppercase(Locale.getDefault()), modifier = Modifier.padding(4.dp))
            }
        }
    }
}