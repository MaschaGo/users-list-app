package com.maschago.githubusersapp.di

import com.maschago.githubusersapp.repository.RestfulAPIRepository
import com.maschago.githubusersapp.repository.RestfulAPIRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(apiRepositoryImpl: RestfulAPIRepositoryImpl) : RestfulAPIRepository

}