package com.maschago.githubusersapp.service

import com.maschago.githubusersapp.Users
import com.maschago.githubusersapp.model.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<Users>

    @GET("users")
    suspend fun getAllUsers(): Response<UsersResponse>
}
