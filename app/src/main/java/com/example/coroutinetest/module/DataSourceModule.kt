package com.example.coroutinetest.module

import com.example.coroutinetest.api.TestService
import com.example.coroutinetest.dataSource.TestDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun getTestDataSource(
        service: TestService
    ): TestDataSource = TestDataSource(service = service)
}