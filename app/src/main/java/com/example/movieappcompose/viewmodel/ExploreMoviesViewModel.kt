package com.example.movieappcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappcompose.model.repository.MovieRepository
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.util.SortBy
import kotlinx.coroutines.launch

class ExploreMoviesViewModel(
    private val repository: MovieRepository = MovieRepository.getInstance()
) : ViewModel() {

    init {
        viewModelScope.launch {
            val voteMovies = fetchMoviesList(SortBy.PopularityDesc, null, 2)
            voteMoviesList.value = voteMovies

            val popularMovies = fetchMoviesList(SortBy.VoteCountDesc, null, 2)
            popularMoviesList.value = popularMovies
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