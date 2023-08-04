package com.example.coroutinetest.api

import com.example.coroutinetest.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TestService {
    @GET("search/web")
    suspend fun getQuery(
        @Query("query") query: String
    ): Response
}