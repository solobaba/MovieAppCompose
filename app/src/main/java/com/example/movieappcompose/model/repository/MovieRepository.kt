package com.example.movieappcompose.model.repository

import android.util.Log
import com.example.movieappcompose.movie.data.remote.MovieWebService
import com.example.movieappcompose.movie.data.remote.response.DiscoverResult
import com.example.movieappcompose.movie.data.remote.response.Movie
import com.example.movieappcompose.movie.data.remote.response.MovieDetail
import com.example.movieappcompose.util.SortBy
import com.google.gson.Gson

class MovieRepository(private val movieWebService: MovieWebService = MovieWebService()
    //private val apiService: ApiService
) {
    private var cachedMeals = listOf<Movie>()

    suspend fun fetchMovies(
        sortBy: SortBy,
        withGenres: String?,
        page: Int
    ): DiscoverResult {
        val response = movieWebService.getMovieListAsync(sortBy.notation, withGenres, page)
        cachedMeals = response.results
        Log.d("RepoServices", Gson().toJson(movieWebService.getMovieListAsync(sortBy.notation, withGenres, page)))
        return response
    }

    fun getMeal(title: String) : Movie? {
        return cachedMeals.firstOrNull {
            it.title == title
        }
    }

    suspend fun getMovieDetails(movieID: Int): MovieDetail {
        return movieWebService.getMovieDetails(movieID)
    }

    companion object {
        @Volatile
        private var instance : MovieRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance?: MovieRepository().also { instance = it }
        }
    }


//    suspend fun fetchMoviesList(
//        sortBy: SortBy,
//        withGenres: String? = null,
//        page: Int
//    ): ApiResult<DiscoverResult?> {
//        return when (val result = safeApiResult {
//            apiService.getMovieListAsync(
//                sortBy.notation,
//                withGenres,
//                page
//            )
//        }) {
//            is ApiResult.Success -> ApiResult.Success(result.data)
//            is ApiResult.Error -> ApiResult.Error(result.exception)
//            is ApiResult.NetworkError -> ApiResult.NetworkError()
//        }
//    }
}