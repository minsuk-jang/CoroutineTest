package com.example.coroutinetest.module

import android.content.Context
import com.example.coroutinetest.BuildConfig
import com.example.coroutinetest.interceptor.TestInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val READ_TIMEOUT = 10L
    private val WRITE_TIMEOUT = 60L
    private val CONNECT_TIMEOUT = 30L


    @Provides
    fun getCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, 1024 * 1024 * 26L)


    @Provides
    fun getOkhttpClient(
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            addInterceptor(TestInterceptor())
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            cache(cache)

            retryOnConnectionFailure(true)
        }.build()
    }

    @Provides
    fun getGson(): Gson = Gson().newBuilder().setLenient().create()

    @Provides
    fun getRetrofit2(
        gson: Gson,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BuildConfig.SERVER_URL)
        }.build()
    }
}