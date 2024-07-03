package com.example.movieappcompose.model.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@JsonClass(generateAdapter = true)
//@Entity(tableName = "all_movies")
@Parcelize
data class Movie(
    val adult: Boolean? = false,
    val backdrop_path: String? = "",
    val genre_ids: List<Int> = listOf(),
    val id: Long = 0,
    val original_language: String? = "",
    val original_title: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val poster_path: String? = "",
    val release_date: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val vote_average: Double? = 0.0,
    val vote_count: Long? = 0
) : Parcelable