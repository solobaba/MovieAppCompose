package com.example.movieappcompose.model.response

import androidx.room.PrimaryKey

data class GenreResult(
    val genres: List<SortFilter>
)

//@Entity(tableName = "all_genre")
data class SortFilter(
    @PrimaryKey
    var id: Int,
    var name: String,
    var idString: String = id.toString(),
    var isSelected: Boolean = false
)