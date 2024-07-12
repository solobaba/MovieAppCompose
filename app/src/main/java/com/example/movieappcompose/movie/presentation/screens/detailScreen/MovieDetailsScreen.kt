package com.example.movieappcompose.movie.presentation.screens.detailScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.component.CircularProgressBar
import com.example.movieappcompose.movie.presentation.component.RetryItem
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import com.example.movieappcompose.movie.presentation.state.MovieDetailsState
import com.example.movieappcompose.util.NetworkUtils
import com.example.movieappcompose.movie.presentation.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson

@Composable
fun MovieDetailsScreen(
    movieID: Int,
    navController: NavController
) {
    val context = LocalContext.current

//    if (!NetworkUtils.isNetworkAvailable(context)) {
//        context.shortToast("Network not available, please check your internet connection")
//    } else {
    val viewModel = hiltViewModel<MovieDetailsViewModel>()
    viewModel.movieID = movieID
    MovieDetailsContent(navController)
    //}
}

@Composable
fun MovieDetailsContent(navController: NavController) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<MovieDetailsViewModel>()
    val movieDetailsState = viewModel.movieDetailsState.collectAsState().value
    //val onEvent = viewModel::onEvent
    val loading by viewModel.loading.collectAsState()
    Log.d("DetailsMovie", Gson().toJson(movieDetailsState.movieDetailDomain))

    Box(modifier = Modifier.fillMaxSize()) {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                RetryItem(
                    modifier = Modifier
                        .width(90.dp)
                        .height(40.dp),
                    message = stringResource(id = R.string.check_your_internet_connection),
                    onClick = {
                        //onEvent(MovieUiEvent.Navigate)
                        navController.popBackStack()
                        navController.navigate(ScreenRoute.Home.route)
                    }
                )
            }
        } else {
            //LoadMovieDetails(navController, movieDetailsState, loading)
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
}

@Composable
fun LoadMovieDetails(
    navController: NavController,
    movieDetailsState: MovieDetailsState,
    loading: Boolean
) {}