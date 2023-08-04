package com.example.coroutinetest.repository

import com.example.coroutinetest.model.Response

interface TestRepository {
    suspend fun getQuery(query: String): Response
}