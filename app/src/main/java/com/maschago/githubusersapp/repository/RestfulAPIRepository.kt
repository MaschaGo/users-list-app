package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.service.NetworkResult

interface RestfulAPIRepository {
    suspend fun fetchAllUsers(): NetworkResult<List<User>>

    suspend fun fetchUserDetails(userId : String): NetworkResult<User>

}