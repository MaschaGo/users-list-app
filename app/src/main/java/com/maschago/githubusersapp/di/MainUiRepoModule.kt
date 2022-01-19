package com.maschago.githubusersapp.di

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import com.maschago.githubusersapp.base.UIRepo
import com.maschago.githubusersapp.ui.MainScreen
import com.maschago.githubusersapp.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MainUiRepoModule {

    @ExperimentalFoundationApi
    @Binds
    abstract fun bindUiRepo(repo: MainScreen): UIRepo<MainViewModel>

}