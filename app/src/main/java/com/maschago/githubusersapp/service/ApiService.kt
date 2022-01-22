package com.maschago.githubusersapp.service

import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    //?per_page=100
    @GET("users")
    suspend fun getUsers(): Response<Users>

    @GET()
    suspend fun getMoreUsers(@Url next: String): Response<Users>

    @GET("users/{userId}")
    suspend fun getUserDetails(@Path(value = "userId") id: String): Response<User>

}
