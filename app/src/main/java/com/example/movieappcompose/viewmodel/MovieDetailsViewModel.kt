package com.example.movieappcompose.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.model.repository.MovieRepository
import com.example.movieappcompose.movie.data.remote.response.MovieDetail
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieRepository = MovieRepository.getInstance(),
) : ViewModel() {

    var movieID: Int = 0

    val loading = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            loading.value = true

            delay(2000)

            val movieDetailResult = getMovieDetails(movieID)
            movieDetails.value = movieDetailResult
            Log.d("ViewModelDetails", Gson().toJson(movieDetails))

            loading.value = false
        }
    }

    val movieDetails: MutableState<MovieDetail> = mutableStateOf(MovieDetail())

    private suspend fun getMovieDetails(movieID: Int): MovieDetail {
        return repository.getMovieDetails(movieID)
    }


//    private val _state = MutableStateFlow(Movie())
//    val state = _state.asStateFlow()
//    private val args = MovieDetailArgs(savedStateHandle)

//    init {
//        getMovieDetails()
//    }

//    private fun getMovieDetails() {
//        val movie = moviesData.results.find { it.title == args.movieState }
//        movie?.let { _state.update { movie } }
//    }

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