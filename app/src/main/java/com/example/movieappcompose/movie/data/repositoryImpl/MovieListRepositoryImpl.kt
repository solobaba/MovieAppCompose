package com.example.movieappcompose.movie.data.repositoryImpl

import com.example.movieappcompose.movie.data.local.MovieListDatabase
import com.example.movieappcompose.movie.data.mappers.toMovie
import com.example.movieappcompose.movie.data.mappers.toMovieEntity
import com.example.movieappcompose.movie.data.remote.ApiService
import com.example.movieappcompose.movie.domain.model.MovieList
import com.example.movieappcompose.movie.domain.repository.MovieListRepository
import com.example.movieappcompose.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val movieListDatabase: MovieListDatabase
): MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<ApiResult<List<MovieList>>> {
        return flow {
            emit(ApiResult.Loading(true))

            val localMovieList = movieListDatabase.movieListDao.getMovieByCategory(category)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(ApiResult.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))

                emit(ApiResult.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                apiService.getMovieList(
                    category,
                    page
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiResult.Error("Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movies ->
                    movies.toMovieEntity(category)
                }
            }

            movieListDatabase.movieListDao.upsertMovieList(movieEntities)

            emit(ApiResult.Success(
                movieEntities.map { it.toMovie(category) }
            ))
            emit(ApiResult.Loading(false))
        }
    }

    override suspend fun getMovieById(id: Int): Flow<ApiResult<MovieList>> {
        return flow {
            emit(ApiResult.Loading(true))

            val movieEntity = movieListDatabase.movieListDao.getMovieById(id)

            if (movieEntity != null) {
                emit(ApiResult.Success(
                    movieEntity.toMovie(movieEntity.category)
                ))

                emit(ApiResult.Loading(false))
                return@flow
            }

            emit(ApiResult.Error("Error no such movie"))
            emit(ApiResult.Loading(false))
        }
    }
}