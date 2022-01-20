package com.maschago.githubusersapp.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.maschago.githubusersapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ErrorViewModel  @Inject constructor() : BaseViewModel() {
    val errorMessage: MutableState<String> = mutableStateOf(String())
}
