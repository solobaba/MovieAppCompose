package com.example.movieappcompose.ui.navigation.navGraphBuilder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieappcompose.ui.screen.detailScreen.MovieDetailsScreen
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel

private const val ROUTE = "movieDetails"

fun NavController.navigateToDetailScreen(movieID: Int) {
    navigate("$ROUTE/$movieID")
}

fun NavGraphBuilder.movieDetailsRoute(navController: NavController) {
    composable("$ROUTE/{${MovieDetailArgs.MOVIE_DETAILS}}",
        arguments = listOf(navArgument(MovieDetailArgs.MOVIE_DETAILS) {
            NavType.IntType
        })) { navBackStackEntry ->
        val movieID = navBackStackEntry.arguments?.getInt(MovieDetailArgs.MOVIE_DETAILS)
        //val viewModel : MovieDetailsViewModel = viewModel()
        MovieDetailsScreen(navController = navController, movieID = movieID)
    }
}

class MovieDetailArgs(savedStateHandle: SavedStateHandle) {
    val movieState: String = checkNotNull(savedStateHandle["movieTitle"])

    companion object {
        const val MOVIE_DETAILS = "movieID"
    }
}