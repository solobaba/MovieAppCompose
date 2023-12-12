package com.example.movieappcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.movieappcompose.model.repository.MovieRepository
import com.example.movieappcompose.model.response.DiscoverResult
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.ui.navigation.navGraphBuilder.MovieDetailArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDetailsViewModel(
    private val moviesData: DiscoverResult,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(Movie())
    val state = _state.asStateFlow()
    private val args = MovieDetailArgs(savedStateHandle)

    init {
        getMovieDetails()
    }

    private fun getMovieDetails() {
        val movie = moviesData.results.find { it.title == args.movieState }
        movie?.let { _state.update { movie } }
    }

//    private val repository: MovieRepository = MovieRepository.getInstance()
//
//    private val _movieState = MutableStateFlow<Movie?>(null)
//    val movieState = _movieState.asStateFlow()
//
//    init {
//        val movieId = savedStateHandle.get<String>("movieTitle") ?: ""
//        _movieState.value = repository.getMeal(movieId)
//    }
}