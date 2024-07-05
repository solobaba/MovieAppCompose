package com.example.movieappcompose.movie.domain.repository

import com.example.movieappcompose.movie.domain.model.MovieList
import com.example.movieappcompose.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<ApiResult<List<MovieList>>>

    suspend fun getMovieById(id: Int) : Flow<ApiResult<MovieList>>
}