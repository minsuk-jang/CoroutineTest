package com.example.coroutinetest.interceptor

import com.example.coroutinetest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "KakaoAK ${BuildConfig.API_KEY}")

        return chain.proceed(request.build())
    }
}