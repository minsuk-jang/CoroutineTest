package com.example.coroutinetest.module

import com.example.coroutinetest.repository.TestRepository
import com.example.coroutinetest.repository.impl.TestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryImpl {


    @Binds
    @Singleton
    abstract fun getTestRepo(impl: TestRepositoryImpl): TestRepository
}