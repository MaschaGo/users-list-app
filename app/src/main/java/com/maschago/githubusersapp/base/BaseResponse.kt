package com.maschago.githubusersapp.base

interface BaseResponse {
    val status: State
    val message: String
}

enum class State {
    SUCCESS, FAILED, UNAUTHORIZED
}