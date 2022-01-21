package com.maschago.githubusersapp.service

import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users?per_page=100")
    suspend fun getUsers(): Response<Users>

    @GET("users/{userId}")
    suspend fun getUserDetails(@Path(value = "userId") id: String): Response<User>

}
