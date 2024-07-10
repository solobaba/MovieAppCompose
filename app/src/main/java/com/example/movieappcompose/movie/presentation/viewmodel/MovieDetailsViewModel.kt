package com.example.movieappcompose.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.movie.domain.repository.MovieListRepository
import com.example.movieappcompose.movie.presentation.state.MovieDetailsState
import com.example.movieappcompose.util.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    var movieID: Int = 0
    private val _movieDetailsState = MutableStateFlow(MovieDetailsState())
    val movieDetailsState = _movieDetailsState.asStateFlow()

    val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true

            delay(2000)

            getMovieDetails(movieID)

            _loading.value = false
        }
    }

    private fun getMovieDetails(movieID: Int) {
        viewModelScope.launch {
            _movieDetailsState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovieDetails(movieID).collectLatest { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        _movieDetailsState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is ApiResult.Error -> {
                        _movieDetailsState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is ApiResult.Success -> {
                        result.data.let { movieDetails ->
                            _movieDetailsState.update {
                                it.copy(
                                    movieDetailDomain = movieDetails
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}