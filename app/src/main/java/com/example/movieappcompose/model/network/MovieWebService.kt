package com.example.movieappcompose.model.network

import android.util.Log
import com.example.movieappcompose.model.response.DiscoverResult
import com.example.movieappcompose.model.response.GenreResult
import com.example.movieappcompose.model.response.MovieDetail
import com.example.movieappcompose.util.SortBy
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class MovieWebService {

    private var api: Service

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getClient())
            .build()

        api = retrofit.create(Service::class.java)
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

        Log.d("FetchMovieServices", Gson().toJson(api.getMovieListAsync(sortBy, withGenres, page)))
        return api.getMovieListAsync(sortBy, withGenres, page)
    }

    interface Service {
        @Headers(Constants.apiToken)
        @GET("/3/discover/movie")
        suspend fun getMovieListAsync(
            @Query("sort_by") sortBy: String,
            @Query("with_genres") withGenres: String? = null,
            @Query("page") page: Int = 1
            //@Query("api_key") apiKey: String = Constants.apiKey
        ): DiscoverResult

        @GET("/3/movie/{movieId}")
        suspend fun getMovieDetails(
            @Path("movieId") movieId: Int = -1,
            @Query("api_key") apiKey: String = Constants.apiKey
        ): Response<MovieDetail>

        @GET("/3/genre/movie/list")
        suspend fun getAllGenreList(
            @Query("api_key") apiKey: String = Constants.apiKey
        ): Response<GenreResult>
    }
}