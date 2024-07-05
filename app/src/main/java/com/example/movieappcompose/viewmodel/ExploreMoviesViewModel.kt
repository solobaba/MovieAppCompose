package com.example.movieappcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.model.repository.MovieRepository
import com.example.movieappcompose.movie.data.remote.response.Movie
import com.example.movieappcompose.util.SortBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ExploreMoviesViewModel(
    private val repository: MovieRepository = MovieRepository.getInstance()
) : ViewModel() {

    val _loading = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            _loading.value = true

            delay(2000)

            val voteMovies = fetchMoviesList(SortBy.PopularityDesc, null, 2)
            voteMoviesList.value = voteMovies

            val popularMovies = fetchMoviesList(SortBy.VoteCountDesc, null, 2)
            popularMoviesList.value = popularMovies

            _loading.value = false
        }
    }

    val voteMoviesList: MutableState<List<Movie>> = mutableStateOf(emptyList<Movie>())
    val popularMoviesList: MutableState<List<Movie>> = mutableStateOf(emptyList<Movie>())

    private suspend fun fetchMoviesList(
        sortBy: SortBy,
        withGenres: String?,
        page: Int
    ) : List<Movie> {
        return repository.fetchMovies(sortBy, null, page).results
    }
}