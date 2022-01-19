package com.maschago.githubusersapp.ui



sealed class UIState<T> {
    class Loading<T> : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Failed<T>(val message: String) : UIState<T>()

    val isLoading get() = this is Loading

    val isSuccess get() = this is Success

    val isFailed get() = this is Failed

    companion object {
        fun <T> loading() = Loading<T>()
        fun <T> success(data: T) = Success(data)
        fun <T> failed(message: String) = Failed<T>(message)
    }
}