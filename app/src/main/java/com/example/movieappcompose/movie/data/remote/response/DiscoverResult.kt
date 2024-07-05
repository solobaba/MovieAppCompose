package com.example.movieappcompose.movie.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DiscoverResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)