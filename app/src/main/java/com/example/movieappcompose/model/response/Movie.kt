package com.example.movieappcompose.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.io.Serializable

//@Entity(tableName = "all_movies")
data class Movie(
    //@PrimaryKey
    val id: Int = -1,
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Double? = null,
    val vote_count: Int? = null
): Serializable