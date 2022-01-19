package com.maschago.githubusersapp.model

import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.base.BaseResponse
import com.maschago.githubusersapp.base.State

data class UsersResponse(

    override val status: State,
    override val message: String,
    val users: List<User> = emptyList()
) : BaseResponse