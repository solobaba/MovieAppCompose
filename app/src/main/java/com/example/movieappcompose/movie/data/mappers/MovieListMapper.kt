package com.example.movieappcompose.movie.data.mappers

import com.example.movieappcompose.movie.data.remote.response.Movie
import com.example.movieappcompose.movie.data.local.MovieListEntity
import com.example.movieappcompose.movie.domain.model.MovieList

fun Movie.toMovieEntity(
    category: String
): MovieListEntity {
    return MovieListEntity(
        adult = adult ?: false,
        backdrop_path = backdrop_path ?: "",
        id = id ?: -1,
        category = category,
        original_language = original_language ?: "",
        original_title = original_title ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        video = video ?: false,
        vote_average = vote_average ?: 0.0,
        vote_count = vote_count ?: 0,

        genre_ids = try {
            genre_ids?.joinToString(",") ?: "-1, -2"
        } catch (e: Exception) {
            "-1, -2"
        }
    )
}

fun MovieListEntity.toMovie(
    category: String
): MovieList {
    return MovieList(
        adult = adult,
        backdrop_path = backdrop_path,
        id = id,
        category = category,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count,

        genre_ids = try {
            genre_ids.split(",").map { it.toInt() }
        } catch (e: Exception) {
            listOf(-1, -2)
        }
    )
}