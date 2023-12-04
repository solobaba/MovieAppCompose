package com.example.movieappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappcompose.ui.screen.ID
import com.example.movieappcompose.ui.screen.ScreenRoute
import com.example.movieappcompose.ui.screen.detailScreen.MovieDetailsScreen
import com.example.movieappcompose.ui.screen.exploreScreen.ExploreMoviesList
import com.example.movieappcompose.ui.screen.homeScreen.MoviesHome
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel

@Composable
fun MoviesAppNavHost(iconClickAction: (Int) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            MoviesHome(navController, iconClickAction) { navigationId ->
                if (navigationId == 0 || navigationId == 1) {
                    navController.navigate( "explorerMoviesList/$navigationId")
                } else {
                    navController.navigate( "movieDetails/$navigationId")
                }
            }
        }

//        composable(ScreenRoute.Home.route) {
//            MoviesHome(navController) { movies_id ->
//                navController.navigate( "movieDetails/$movies_id")
//            }
//        }

        composable(ScreenRoute.Explore.route,
            arguments = listOf(navArgument(name = ID) {
                type = NavType.IntType
            })) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(ID)
            ExploreMoviesList(navController, id)
        }

        composable(ScreenRoute.Details.route,
            arguments = listOf(navArgument(name = "movies_id") {
                type = NavType.IntType
            })) { navBackStackEntry ->
            val viewModel : MovieDetailsViewModel = viewModel()
            MovieDetailsScreen(navController, viewModel.movieState.value)
        }

//        composable(ScreenRoute.Details.route,
//            arguments = listOf(navArgument(name = "moviesString") {
//                type = NavType.StringType
//            })) { navBackStackEntry ->
//            navBackStackEntry.arguments?.getString("moviesString")?.let { jsonString ->
//                val movies = jsonString .fromJson(Movie::class.java)
//                MovieDetailsScreen(navController, movies)
//            }
//        }
    }
}