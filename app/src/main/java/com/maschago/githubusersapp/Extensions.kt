package com.maschago.githubusersapp

import com.maschago.githubusersapp.service.NetworkResult
import retrofit2.Response

fun <T> Response<T>.toNetworkResult(): NetworkResult<T> {
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
