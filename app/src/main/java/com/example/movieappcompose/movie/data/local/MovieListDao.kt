package com.example.movieappcompose.movie.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieListDao {
    @Upsert
    suspend fun upsertMovieList(movieListEntity: List<MovieListEntity>)

    @Query("SELECT * FROM MovieListEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieListEntity

    @Query("SELECT * FROM MovieListEntity WHERE category = :category")
    suspend fun getMovieByCategory(category: String): List<MovieListEntity>
}