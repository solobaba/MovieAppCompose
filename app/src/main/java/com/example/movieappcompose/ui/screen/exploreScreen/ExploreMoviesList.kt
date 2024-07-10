package com.example.movieappcompose.ui.screen.exploreScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.screens.PopularListMovies
import com.example.movieappcompose.movie.presentation.viewmodel.MovieListViewmodel
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.ui.component.Toolbar
import com.example.movieappcompose.ui.screen.homeScreen.PopularMovies
import com.example.movieappcompose.util.Category
import com.example.movieappcompose.util.NetworkUtils
import com.example.movieappcompose.util.shortToast
import com.example.movieappcompose.viewmodel.ExploreMoviesViewModel

@Composable
fun ExploreMoviesList(navController: NavController, id: String?) {
    Column {
        Toolbar(
            title = stringResource(R.string.explore),
            icon = rememberAsyncImagePainter(R.drawable.ic_baseline_arrow_black_back_24)
        ) {
            navController.popBackStack()
        }
        MovieList(id, navController)
    }
}

@Composable
fun MovieList(id: String?, navController: NavController) {
    val mContext = LocalContext.current
    if (!NetworkUtils.isNetworkAvailable(mContext)) {
        mContext.shortToast("Network not available, please check your internet connection")
    } else {
        if (id == "0") {
            val viewModel = hiltViewModel<MovieListViewmodel>()
            val movieState = viewModel.movieState.collectAsState().value
            val loading by viewModel.loading.collectAsState()
            val onEvent = viewModel::onEvent

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularIndeterminateProgressBar(isDisplayed = loading)

                LazyColumn(contentPadding = PaddingValues(10.dp)) {
                    items(movieState.nowShowingMovieList.size) { movie ->
                        PopularListMovies(navController, movieState.nowShowingMovieList[movie])

                        if (movie >= movieState.nowShowingMovieList.size - 1 && !movieState.isLoading) {
                            onEvent(MovieUiEvent.Paginate(Category.NOW_SHOWING))
                        }
                    }
                }
            }
        } else {
            val viewModel = hiltViewModel<MovieListViewmodel>()
            val movieState = viewModel.movieState.collectAsState().value
            val loading by viewModel.loading.collectAsState()
            val onEvent = viewModel::onEvent

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularIndeterminateProgressBar(isDisplayed = loading)

                LazyColumn(contentPadding = PaddingValues(10.dp)) {
                    items(movieState.popularMovieList.size) { movie ->
                        PopularListMovies(navController, movieState.popularMovieList[movie])

                        if (movie >= movieState.popularMovieList.size - 1 && !movieState.isLoading) {
                            onEvent(MovieUiEvent.Paginate(Category.POPULAR))
                        }
                    }
                }
            }
        }
    }
}