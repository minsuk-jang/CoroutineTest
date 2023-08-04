package com.example.coroutinetest.dataSource

import com.example.coroutinetest.api.TestService
import com.example.coroutinetest.model.Response
import javax.inject.Inject


class TestDataSource @Inject constructor(
    private val service: TestService
) {
    suspend fun getQuery(query: String): Response {
        return service.getQuery(query = query)
    }
}