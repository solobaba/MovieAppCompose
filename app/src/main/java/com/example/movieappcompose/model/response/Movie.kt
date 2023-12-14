package com.example.movieappcompose.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//@Entity(tableName = "all_movies")
@Parcelize
data class Movie(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val id: Int = 0,
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
) : Parcelable