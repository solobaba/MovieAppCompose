package com.example.movieappcompose.movie.data.remote

import com.example.movieappcompose.movie.data.remote.response.DiscoverResult
import com.example.movieappcompose.movie.data.remote.response.GenreResult
import com.example.movieappcompose.movie.data.remote.response.MovieDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //@Headers(Constants.apiToken)
    @GET("/3/discover/movie")
    suspend fun getMovieList(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constants.apiKey
    ): DiscoverResult

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int = 0,
        @Query("api_key") apiKey: String = Constants.apiKey
    ): MovieDetail

    @GET("/3/genre/movie/list")
    suspend fun getAllGenreList(
        @Query("api_key") apiKey: String = Constants.apiKey
    ): Response<GenreResult>
}