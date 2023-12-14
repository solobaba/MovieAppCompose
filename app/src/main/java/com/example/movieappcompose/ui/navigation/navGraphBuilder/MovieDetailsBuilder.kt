package com.example.movieappcompose.ui.navigation.navGraphBuilder

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieappcompose.ui.screen.detailScreen.MovieDetailsScreen

private const val ROUTE = "movieDetails"

fun NavController.navigateToDetailScreen(movieID: Int) {
    navigate("$ROUTE/$movieID")
}

const val MOVIE_DETAILS = "movieID"

fun NavGraphBuilder.movieDetailsRoute(navController: NavController) {
    composable("$ROUTE/{${MOVIE_DETAILS}}",
        arguments = listOf(navArgument(MOVIE_DETAILS) {
            type = NavType.IntType
        })) { navBackStackEntry ->
        val movieID = navBackStackEntry.arguments?.getInt(MOVIE_DETAILS)
        //val viewModel : MovieDetailsViewModel = viewModel()
        MovieDetailsScreen(navController = navController, movieID = movieID ?: 0)
    }
}