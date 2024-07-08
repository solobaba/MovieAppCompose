package com.example.movieappcompose.movie.presentation.event

sealed interface MovieUiEvent {
    data class Paginate(val category: String) : MovieUiEvent
    object Navigate : MovieUiEvent
}