package com.maschago.githubusersapp.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.maschago.githubusersapp.User
import com.maschago.githubusersapp.base.BaseViewModel
import com.maschago.githubusersapp.repository.RestfulAPIRepository
import com.maschago.githubusersapp.service.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: RestfulAPIRepository) : BaseViewModel() {

    val usersList: MutableState<List<User>> = mutableStateOf(ArrayList())
    val errorMessage: MutableState<String> = mutableStateOf(String())

    init {
        loadAllUsers()
    }

    private fun loadAllUsers() {
        viewModelScope.launch {
            try {
                when (val result = apiRepository.fetchAllUsers()) {
                    is NetworkResult.Payload -> {
                        usersList.value = result.payload
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

    fun retryLoading() {
        loadAllUsers()
    }

}