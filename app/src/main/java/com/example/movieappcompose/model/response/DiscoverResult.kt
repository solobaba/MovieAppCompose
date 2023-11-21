package com.example.movieappcompose.model.response

data class DiscoverResult(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)