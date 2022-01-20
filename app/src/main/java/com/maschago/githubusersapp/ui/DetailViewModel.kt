package com.maschago.githubusersapp.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.base.BaseViewModel
import com.maschago.githubusersapp.navigaton.Screen
import com.maschago.githubusersapp.repository.RestfulAPIRepository
import com.maschago.githubusersapp.service.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiRepository: RestfulAPIRepository,
    private val state: SavedStateHandle,
) : BaseViewModel() {

    private var savedUserId: String = ""

    init {
        state.get<String>(Screen.UsersDetail.ARG_USER_ID)?.let { userId ->
            savedUserId = userId
            loadUserDetails(userId)
        }
    }


    val user: MutableState<User?> = mutableStateOf(null)
    val errorMessage: MutableState<String> = mutableStateOf(String())

    private fun loadUserDetails(userId: String) {
        viewModelScope.launch {
            try {
                when (val result = apiRepository.fetchUserDetails(userId)) {
                    is NetworkResult.Payload -> {
                        user.value = result.payload
                    }
                    is NetworkResult.Error -> {
                        errorMessage.value = result.error
                    }
                }
            } catch (e: Exception) {
                Timber.e("Exception: ${e}, ${e.cause}")
                e.printStackTrace()
            }
        }

    }

    fun retryLoadingUserDetails() {
        loadUserDetails(savedUserId)
    }
}