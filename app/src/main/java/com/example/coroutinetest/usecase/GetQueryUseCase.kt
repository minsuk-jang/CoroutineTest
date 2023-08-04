package com.example.coroutinetest.usecase

import com.example.coroutinetest.model.Response
import com.example.coroutinetest.repository.TestRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetQueryUseCase @Inject constructor(
    private val repository: TestRepository
) {
    suspend operator fun invoke(query: String): Response {
        return repository.getQuery(query = query)
    }
}