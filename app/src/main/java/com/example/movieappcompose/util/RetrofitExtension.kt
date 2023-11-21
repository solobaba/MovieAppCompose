package com.example.movieappcompose.util

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(baseUrl: String, interceptor: Interceptor? = null): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        interceptor?.let { addInterceptor(it) }
    }.build())
    .build()

val logger = HttpLoggingInterceptor.Logger {
    // Bugfender.d("Retrofit", it)
    logTrace(it)
}