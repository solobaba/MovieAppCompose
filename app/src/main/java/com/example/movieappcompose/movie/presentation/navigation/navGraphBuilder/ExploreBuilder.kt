package com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieappcompose.movie.presentation.screens.exploreScreen.ExploreMoviesList

private const val ROUTE = "explorerMoviesList"

fun NavController.navigateToExploreScreen(id: String) {
    navigate("$ROUTE/$id")
}

fun NavGraphBuilder.exploreRoute(navController: NavController) {
    composable("$ROUTE/{${ExploreMoviesArgs.ID}}",
        arguments = listOf(navArgument(ExploreMoviesArgs.ID) {
            NavType.StringType
        })) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString(ExploreMoviesArgs.ID)
        ExploreMoviesList(navController, id)
    }
}

class ExploreMoviesArgs(savedStateHandle: SavedStateHandle) {
    //val movieName: String = checkNotNull(savedStateHandle["id"])

    companion object {
        const val ID = "id"
    }
}