package com.maschago.githubusersapp.repository

import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.base.State
import com.maschago.githubusersapp.service.ApiService
import com.maschago.githubusersapp.service.NetworkResult
import com.maschago.githubusersapp.toNetworkResult
import com.maschago.githubusersapp.utils.getResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

@ExperimentalCoroutinesApi
class RestfulAPIRepositoryImpl @Inject constructor(private val apiService: ApiService) : RestfulAPIRepository {

    override suspend fun fetchAllUsers(): NetworkResult<List<User>> {
        return apiService.getUsers().toNetworkResult()
    }

    override fun getAllNotes(): Flow<NetworkResult<List<User>>> = flow {
        val usersResponse = apiService.getAllUsers().getResponse()

        val state = when (usersResponse.status) {
            State.SUCCESS -> NetworkResult.Payload(usersResponse.users)
            else -> NetworkResult.Error(usersResponse.message)
        }

//        val state = when (val result = notesResponse) {
//            is NetworkResult.Payload<*> -> {
//                result.payload
//            }
//            is NetworkResult.Error -> {
//               result.error
//            }
//        }

        emit(state)
    }
        .catch {emit(NetworkResult.Error("Can't sync latest info")) }

}
