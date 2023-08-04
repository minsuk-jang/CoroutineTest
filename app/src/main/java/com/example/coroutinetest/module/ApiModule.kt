package com.example.coroutinetest.module

import com.example.coroutinetest.api.TestService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun getService(
        retrofit: Retrofit
    ): TestService = retrofit.create(TestService::class.java)
}