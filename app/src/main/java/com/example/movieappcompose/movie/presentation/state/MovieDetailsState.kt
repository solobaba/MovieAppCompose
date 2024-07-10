package com.example.movieappcompose.movie.presentation.state

import com.example.movieappcompose.movie.domain.model.MovieDetailDomain

data class MovieDetailsState(
    var isLoading : Boolean = false,
    val movieDetailDomain: MovieDetailDomain? = null
)