package com.example.movieappcompose.ui.screen.exploreScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.ui.component.Toolbar
import com.example.movieappcompose.ui.screen.homeScreen.PopularMovies
import com.example.movieappcompose.util.shortToast
import com.example.movieappcompose.viewmodel.ExploreMoviesViewModel

@Composable
fun ExploreMoviesList(navController: NavController, id: String?) {
    val mContext = LocalContext.current

    Column {
        Toolbar(
            title = "Explore",
            icon = rememberAsyncImagePainter(R.drawable.ic_baseline_arrow_black_back_24)
        ) {
            navController.popBackStack()
        }
        mContext.shortToast(id.toString())
        MovieList(id, navController)
    }
}

@Composable
fun MovieList(id: String?, navController: NavController) {
    if (id == "0") {

        val viewModel: ExploreMoviesViewModel = viewModel()
        val horizontalMovies = viewModel.voteMoviesList.value
        val loading by viewModel._loading.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            CircularIndeterminateProgressBar(isDisplayed = loading)

            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                items(horizontalMovies) { movie ->
                    PopularMovies(navController, movie)
                }
            }
        }
    } else {
        val viewModel: ExploreMoviesViewModel = viewModel()
        val horizontalMovies = viewModel.popularMoviesList.value
        val loading by viewModel._loading.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            CircularIndeterminateProgressBar(isDisplayed = loading)

            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                items(horizontalMovies) { movie ->
                    PopularMovies(navController, movie)
                }
            }
        }
    }
}