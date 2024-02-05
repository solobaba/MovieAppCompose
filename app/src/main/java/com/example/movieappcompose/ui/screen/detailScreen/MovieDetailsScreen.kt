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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.util.shortToast
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson

@Composable
fun MovieDetailsScreen(
    movieID: Int,
    navController: NavController
) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val loading by viewModel.loading.collectAsState()

    CircularIndeterminateProgressBar(isDisplayed = loading)

    viewModel.movieID = movieID

    //val mContext = LocalContext.current
    //mContext.shortToast(viewModel.movieID.toString())

    MovieDetailsContent(navController)
}

@Composable
fun MovieDetailsContent(navController: NavController) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val movieDetails = viewModel.movieDetails.value
    Log.d("DetailsMovie", Gson().toJson(movieDetails))

    Box(modifier = Modifier.fillMaxSize()) {
        UpperSection(
            navController,
            movieDetails.backdrop_path,
            movieDetails.homepage,
            movieDetails.id
        )
        BottomSlidingPanel(
            movieDetails.genres,
            movieDetails.title ?: "Title",
            movieDetails.overview,
            movieDetails.popularity,
            movieDetails.status,
            movieDetails.release_date,
            Modifier.align(Alignment.BottomEnd)
        )
    }
}