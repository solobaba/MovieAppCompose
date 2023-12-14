package com.example.movieappcompose.model.network

import com.example.movieappcompose.model.response.DiscoverResult
import com.example.movieappcompose.model.response.MovieDetail
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MovieWebService {

    private var api: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getClient())
            .build()

        api = retrofit.create(ApiService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .readTimeout(200, TimeUnit.SECONDS)
            .connectTimeout(200, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    suspend fun getMovieListAsync(
        sortBy: String,
        withGenres: String?,
        page: Int
    ): DiscoverResult {
        return api.getMovieListAsync(sortBy, withGenres, page)
    }

    suspend fun getMovieDetails(
        movieId: Int
    ): MovieDetail {
        return api.getMovieDetails(movieId)
    }
}