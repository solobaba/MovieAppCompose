package com.example.movieappcompose.movie.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieListEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: String,
    @PrimaryKey
    val id: Int = 0,
    val category: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
)