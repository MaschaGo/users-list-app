package com.maschago.githubusersapp.service

sealed class NetworkResult<out T> {
    data class Payload<out T>(val payload: T) : NetworkResult<T>()
    data class Error<out T>(val error: String) : NetworkResult<T>()
}

inline fun <I, O> NetworkResult<I>.map(mapper: (I) -> O): NetworkResult<O> {
    return when (this) {
        is NetworkResult.Payload -> NetworkResult.Payload(mapper(this.payload))
        is NetworkResult.Error -> NetworkResult.Error(this.error)
    }
}
