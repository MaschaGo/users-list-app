package com.maschago.githubusersapp.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.maschago.githubusersapp.navigaton.AppNavigation
import com.maschago.githubusersapp.ui.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
abstract class BaseActivity <T: BaseViewModel> : ComponentActivity() {

    @Inject
    lateinit var uiRepo: UIRepo<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemedContent()
        }
    }

    @Composable
    open fun UnthemedContent() {
        Surface(color = surfaceColor()) {
            uiRepo.ScreenComposition(viewModel = viewModel(), extras())
        }
    }

    @Composable
    open fun ThemedContent() {
        ComposeAppTheme {
            UnthemedContent()
        }
    }

    abstract fun viewModel() : T

    open fun surfaceColor() = Color(0xFFEEEEEE)

    open fun extras(): Bundle? = null
}