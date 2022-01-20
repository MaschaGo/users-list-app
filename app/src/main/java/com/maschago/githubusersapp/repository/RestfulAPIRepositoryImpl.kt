package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.service.ApiService
import com.maschago.githubusersapp.service.NetworkResult
import com.maschago.githubusersapp.toNetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RestfulAPIRepositoryImpl @Inject constructor(private val apiService: ApiService) : RestfulAPIRepository {

    override suspend fun fetchAllUsers(): NetworkResult<List<User>> {
        return apiService.getUsers().toNetworkResult()
    }

    override suspend fun fetchUserDetails(userId: String): NetworkResult<User> {
        return apiService.getUserDetails(userId).toNetworkResult()
    }

}
