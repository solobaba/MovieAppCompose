package com.example.movieappcompose.movie.presentation.viewmodel

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

class MovieDetails(
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
}