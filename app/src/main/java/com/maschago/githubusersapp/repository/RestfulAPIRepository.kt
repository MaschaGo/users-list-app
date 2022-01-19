package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.service.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RestfulAPIRepository {
    suspend fun fetchAllUsers(): NetworkResult<List<User>>

    fun getAllNotes(): Flow<NetworkResult<List<User>>>
}