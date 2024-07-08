package com.example.movieappcompose.movie.data.remote

import com.example.movieappcompose.movie.data.remote.response.DiscoverResult
import com.example.movieappcompose.movie.data.remote.response.MovieDetail
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MovieWebService {

    private var api: ApiService
    private val moshi: Moshi? = null
        //Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private fun provideMoshi(): Moshi {
        synchronized(this) {
            return moshi ?: Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
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
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
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