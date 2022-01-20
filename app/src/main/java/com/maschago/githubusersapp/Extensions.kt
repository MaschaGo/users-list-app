package com.maschago.githubusersapp

import com.maschago.githubusersapp.service.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun<T> Response<T>.toNetworkResult() : NetworkResult<T> {
    return try {
        if (this.isSuccessful) {
            NetworkResult.Payload(this.body()!!)
        } else {
            NetworkResult.Error(this.errorBody()?.string() ?: "Unknown error")
        }
    } catch (throwable: Throwable) {
        NetworkResult.Error(throwable.message ?: "Unknown error")
    }
}
