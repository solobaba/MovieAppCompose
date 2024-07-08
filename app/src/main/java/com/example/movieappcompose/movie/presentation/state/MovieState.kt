package com.example.movieappcompose.movie.presentation.state

import com.example.movieappcompose.movie.domain.model.MovieList

data class MovieState(
    var isLoading : Boolean = false,

    val isCurrentHomeScreen : Boolean = true,

    val nowShowingMovieListPage: Int = 1,
    val popularMovieListPage: Int = 1,

    val nowShowingMovieList: List<MovieList> = emptyList(),
    val popularMovieList: List<MovieList> = emptyList(),
)
