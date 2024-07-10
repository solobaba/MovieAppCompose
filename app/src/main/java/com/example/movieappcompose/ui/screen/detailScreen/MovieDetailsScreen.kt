package com.example.movieappcompose.ui.screen.detailScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappcompose.ui.component.CircularProgressBar
import com.example.movieappcompose.util.NetworkUtils
import com.example.movieappcompose.util.shortToast
import com.example.movieappcompose.movie.presentation.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson

@Composable
fun MovieDetailsScreen(
    movieID: Int,
    navController: NavController
) {
    val context = LocalContext.current
    if (!NetworkUtils.isNetworkAvailable(context)) {
        context.shortToast("Network not available, please check your internet connection")
    } else {
        val viewModel = hiltViewModel<MovieDetailsViewModel>()
        viewModel.movieID = movieID

        MovieDetailsContent(navController)
    }
}

@Composable
fun MovieDetailsContent(navController: NavController) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value
    val loading by viewModel.loading.collectAsState()
    Log.d("DetailsMovie", Gson().toJson(movieDetailsState.movieDetailDomain))

    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressBar(isDisplayed = loading)

        UpperSection(
            navController,
            movieDetailsState.movieDetailDomain?.backdrop_path,
            movieDetailsState.movieDetailDomain?.homepage,
            movieDetailsState.movieDetailDomain?.id,
            movieDetailsState.movieDetailDomain?.title ?: ""
        )
        BottomSlidingPanel(
            movieDetailsState.movieDetailDomain?.genres ?: emptyList(),
            movieDetailsState.movieDetailDomain?.title ?: "Title",
            movieDetailsState.movieDetailDomain?.overview,
            movieDetailsState.movieDetailDomain?.popularity ?: 0.0,
            movieDetailsState.movieDetailDomain?.status,
            movieDetailsState.movieDetailDomain?.release_date,
            Modifier.align(Alignment.BottomEnd)
        )
    }
}