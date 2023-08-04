package com.example.coroutinetest.repository.impl

import com.example.coroutinetest.dataSource.TestDataSource
import com.example.coroutinetest.model.Response
import com.example.coroutinetest.repository.TestRepository
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(
    private val dataSource: TestDataSource
) : TestRepository {
    override suspend fun getQuery(query: String): Response {
        return dataSource.getQuery(query = query)
    }
}