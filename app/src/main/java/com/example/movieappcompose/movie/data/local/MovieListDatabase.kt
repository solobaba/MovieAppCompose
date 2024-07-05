package com.example.movieappcompose.movie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieListEntity::class],
    version = 1
)
abstract class MovieListDatabase : RoomDatabase() {
    abstract val movieListDao: MovieListDao
}