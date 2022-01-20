package com.maschago.githubusersapp.di

import androidx.compose.foundation.ExperimentalFoundationApi
import com.maschago.githubusersapp.base.UIRepo
import com.maschago.githubusersapp.ui.ScreenUIRepo
import com.maschago.githubusersapp.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MainUiRepoModule {

    @ExperimentalFoundationApi
    @Binds
    abstract fun bindUiRepo(repo: ScreenUIRepo): UIRepo<MainViewModel>

}