package com.maschago.githubusersapp.ui

import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.maschago.githubusersapp.base.BaseActivity
import com.maschago.githubusersapp.ui.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    private val viewModel: MainViewModel by viewModels()

    override fun viewModel() = viewModel

    @Composable
    override fun ThemedContent() {
        ComposeAppTheme {
            UnthemedContent()
        }
    }

}