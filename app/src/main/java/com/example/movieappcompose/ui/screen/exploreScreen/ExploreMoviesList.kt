package com.example.movieappcompose.ui.screen.exploreScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.component.Toolbar
import com.example.movieappcompose.ui.screen.homeScreen.PopularMovies
import com.example.movieappcompose.viewmodel.ExploreMoviesViewModel

@Composable
fun ExploreMoviesList(navController: NavHostController, id: Int?) {
    Column {
        Toolbar(
            title = "Explore",
            icon = rememberImagePainter(R.drawable.ic_baseline_arrow_black_back_24)
        ) {
            navController.navigateUp()
        }
        MovieList(id, navController)
    }
}

@Composable
fun MovieList(id: Int?, navController: NavHostController) {
    if (id == 0) {
        val viewModel: ExploreMoviesViewModel = viewModel()
        val horizontalMovies = viewModel.voteMoviesList.value

        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(horizontalMovies) { movie ->
                PopularMovies(navController, movie) {

                }
            }
        }
    } else {
        val viewModel: ExploreMoviesViewModel = viewModel()
        val horizontalMovies = viewModel.popularMoviesList.value

        LazyColumn(contentPadding = PaddingValues(10.dp)) {
            items(horizontalMovies) { movie ->
                PopularMovies(navController, movie) {

                }
            }
        }
    }
}