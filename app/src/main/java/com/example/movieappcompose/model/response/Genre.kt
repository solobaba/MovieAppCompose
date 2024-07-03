package com.example.movieappcompose.model.response

import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResult(
    val genres: List<SortFilter>
)

@JsonClass(generateAdapter = true)
//@Entity(tableName = "all_genre")
data class SortFilter(
    @PrimaryKey
    var id: Int,
    var name: String,
    var idString: String = id.toString(),
    var isSelected: Boolean = false
)