package com.maschago.githubusersapp.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

interface UIRepo<M : ViewModel> {
    @Composable
    fun ScreenComposition(viewModel: M, extras: Bundle?)
}