package com.example.movieappcompose.model.network

import com.example.movieappcompose.model.response.DiscoverResult
import com.example.movieappcompose.model.response.GenreResult
import com.example.movieappcompose.model.response.MovieDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers(Constants.apiToken)
    @GET("/3/discover/movie")
    suspend fun getMovieListAsync(
        @Query("sort_by") sortBy: String,
        @Query("with_genres") withGenres: String ?= null,
        @Query("page") page: Int,
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

val Retrofit.movieApiService: ApiService
    get() = create(ApiService::class.java)