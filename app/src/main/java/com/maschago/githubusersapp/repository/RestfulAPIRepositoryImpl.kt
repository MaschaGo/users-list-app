package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.model.Users
import com.maschago.githubusersapp.service.ApiService
import com.maschago.githubusersapp.service.NetworkResult
import com.maschago.githubusersapp.toNetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RestfulAPIRepositoryImpl @Inject constructor(private val apiService: ApiService) : RestfulAPIRepository {

    override suspend fun fetchAllUsers(): ResponseWithPagination {
        val result = apiService.getUsers()
        val url = parsUrlFromResponseHeader(result.headers()["link"])

        return ResponseWithPagination(result.toNetworkResult(), url)
    }

    override suspend fun loadNextPage(url: String): ResponseWithPagination {
        val result = apiService.getMoreUsers(url)
        val nextUrl = parsUrlFromResponseHeader(result.headers()["link"])

        return ResponseWithPagination(result.toNetworkResult(), nextUrl)
    }

    override suspend fun fetchUserDetails(userId: String): NetworkResult<User> {
        return apiService.getUserDetails(userId).toNetworkResult()
    }

    private fun parsUrlFromResponseHeader(headerLink: String?): String {
        // https://datatracker.ietf.org/doc/html/rfc5988#page-6
        //  Link           = "Link" ":" #link-value
        //  link-value     = "<" URI-Reference ">" *( ";" link-param )
        //  link-param     = ( ( "rel" "=" relation-types )

        val regex = """<(\S*)>; rel=\"next\"""".toRegex()
        return regex.find(headerLink.toString())?.destructured?.component1() ?: ""
    }
}

class ResponseWithPagination(val usersNetworkResult: NetworkResult<Users>, val nextLink: String)

