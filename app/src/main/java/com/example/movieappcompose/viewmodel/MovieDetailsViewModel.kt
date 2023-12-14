package com.example.movieappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.movieappcompose.model.repository.MovieRepository
import com.example.movieappcompose.model.response.Movie

class MovieDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val repository: MovieRepository = MovieRepository.getInstance()

    val movieState = mutableStateOf<Movie?>(null)

    init {
        val movieId = savedStateHandle.get<Int>("movies_id") ?: ""
        movieState.value = repository.getMeal(movieId as Int)
    }
}

