package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.service.ApiService
import com.maschago.githubusersapp.service.NetworkResult
import com.maschago.githubusersapp.toNetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RestfulAPIRepositoryImpl @Inject constructor(private val apiService: ApiService) : RestfulAPIRepository {

    //ToDo: add specific UnitTests
    override suspend fun fetchAllUsers(): NetworkResult<List<User>> {
        return apiService.getUsers().toNetworkResult()
    }

    override suspend fun fetchUserDetails(userId: String): NetworkResult<User> {
        return apiService.getUserDetails(userId).toNetworkResult()
    }

    //ToDo: pagination using link for next page.
    // S. https://docs.github.com/en/rest/reference/users#list-users
    // https://docs.github.com/en/rest/overview/resources-in-the-rest-api#link-header
    // https://datatracker.ietf.org/doc/html/rfc5988#page-6

}
