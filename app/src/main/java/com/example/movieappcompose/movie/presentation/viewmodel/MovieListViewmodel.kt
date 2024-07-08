package com.example.movieappcompose.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.movie.domain.repository.MovieListRepository
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.state.MovieState
import com.example.movieappcompose.util.ApiResult
import com.example.movieappcompose.util.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewmodel @Inject constructor(
    private val movieListRepository: MovieListRepository
): ViewModel() {
    private val _movieState = MutableStateFlow(MovieState())
    val movieState = _movieState.asStateFlow()

    val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            _loading.value = true
            delay(2000)
            fetchNowShowingMovie(false)
            _loading.value = true
            fetchPopularMovie(false)
            _loading.value = false
        }
    }

    fun onEvent(event: MovieUiEvent) {
        when (event) {
            MovieUiEvent.Navigate -> {
                _movieState.update {
                    it.copy(
                        isCurrentHomeScreen = !movieState.value.isCurrentHomeScreen
                    )
                }
            }
            is MovieUiEvent.Paginate -> {
                if (event.category == Category.NOW_SHOWING) {
                    fetchNowShowingMovie(true)
                } else if (event.category == Category.POPULAR) {
                    fetchPopularMovie(true)
                }
            }
        }
    }

    private fun fetchNowShowingMovie(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.NOW_SHOWING,
                movieState.value.nowShowingMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _movieState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is ApiResult.Success -> {
                        result.data?.let { nowShowingList ->
                            _movieState.update {
                                it.copy(
                                    nowShowingMovieList = movieState.value.nowShowingMovieList
                                            + nowShowingList.shuffled(),
                                    nowShowingMovieListPage = movieState.value.nowShowingMovieListPage + 1
                                )
                            }
                        }
                    }
                    is ApiResult.Loading -> {
                        _movieState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }
        }
    }

    private fun fetchPopularMovie(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieState.update {
                it.copy(isLoading = true)
            }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieState.value.popularMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _movieState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is ApiResult.Success -> {
                        result.data?.let { popularList ->
                            _movieState.update {
                                it.copy(
                                    isLoading = false,
                                    popularMovieList = movieState.value.popularMovieList
                                            + popularList.shuffled(),
                                    popularMovieListPage = movieState.value.popularMovieListPage + 1
                                )
                            }
                        }
                    }
                    is ApiResult.Loading -> {
                        _movieState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }
        }
    }
}