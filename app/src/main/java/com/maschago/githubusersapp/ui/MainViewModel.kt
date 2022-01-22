package com.maschago.githubusersapp.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.maschago.githubusersapp.base.BaseViewModel
import com.maschago.githubusersapp.model.User
import com.maschago.githubusersapp.model.UsersWithPagination
import com.maschago.githubusersapp.repository.RestfulAPIRepository
import com.maschago.githubusersapp.service.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: RestfulAPIRepository) : BaseViewModel() {

    private var currentUsersList: List<User> = emptyList()
    val errorMessage: MutableState<String> = mutableStateOf(String())
    private val nextUrl: MutableState<String> = mutableStateOf(String())


    private val _newsState = MutableStateFlow(UsersWithPagination(emptyList(), ""))
    val newsState: StateFlow<UsersWithPagination> get() = _newsState

    init {
        loadAllUsers()
    }

    private fun loadAllUsers() {
        viewModelScope.launch {
            try {
                val fullResult = apiRepository.fetchAllUsers()
                when (val result = fullResult.usersNetworkResult) {
                    is NetworkResult.Payload -> {
                        currentUsersList = result.payload
                        nextUrl.value = fullResult.nextLink
                        _newsState.value = UsersWithPagination(result.payload, fullResult.nextLink)
                    }
                    is NetworkResult.Error -> {
                        errorMessage.value = result.error
                    }
                }
            } catch (e: Exception) {
                Timber.e("Exception: ${e}, ${e.cause}")
                errorMessage.value = "Something went wrong.\n" +
                        "Exception: ${e}, ${e.cause}"
                e.printStackTrace()
            }
        }

    }

    fun retryLoading() {
        loadAllUsers()
    }

    fun loadNextPage() {

        if (nextUrl.value.isNotEmpty()) {
            viewModelScope.launch {
                try {
                    val fullResult = apiRepository.loadNextPage(_newsState.value.nextLink)
                    when (val result = fullResult.usersNetworkResult) {
                        is NetworkResult.Payload -> {
                            val combinedList = currentUsersList + result.payload
                            _newsState.value = UsersWithPagination(combinedList, fullResult.nextLink)
                            currentUsersList = combinedList
                        }
                        is NetworkResult.Error -> {
                            errorMessage.value = result.error
                        }
                    }
                } catch (e: Exception) {
                    Timber.e("Exception: ${e}, ${e.cause}")
                    errorMessage.value = "Something went wrong.\n" +
                            "Exception: ${e}, ${e.cause}"
                    e.printStackTrace()
                }
            }
        }
    }

}