package com.example.movieappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.ui.screen.ID
import com.example.movieappcompose.ui.screen.ScreenRoute
import com.example.movieappcompose.ui.screen.detailScreen.MovieDetailsScreen
import com.example.movieappcompose.ui.screen.exploreScreen.ExploreMoviesList
import com.example.movieappcompose.ui.screen.homeScreen.MoviesHome
import com.example.movieappcompose.util.fromJson

@Composable
fun MoviesAppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        composable(ScreenRoute.Home.route) {
            MoviesHome(navController) { navigationId ->
                navController.navigate( "explorerMoviesList/$navigationId")
            }
        }

        composable(ScreenRoute.Explore.route,
            arguments = listOf(navArgument(name = ID) {
                type = NavType.StringType
            })) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString(ID)
            ExploreMoviesList(navController, id)
        }

        composable("movieDetails/{moviesString}",
            arguments = listOf(navArgument(name = "moviesString") {
                type = NavType.StringType
            })) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("moviesString")?.let { jsonString ->
                val movies = jsonString .fromJson(Movie::class.java)
                MovieDetailsScreen(navController, movies)
            }
        }

//        composable("moviesHome") {
//            MoviesHome() { navigationId ->
//                navController.navigate( "explorerMoviesList/$navigationId")
//            }
//        }
//
//        composable("explorerMoviesList/{id}",
//            arguments = listOf(navArgument(name = "id") {
//                type = NavType.IntType
//            })) { navBackStackEntry ->
//            val id = navBackStackEntry.arguments?.getInt("id")
//            ExploreMoviesList(navController, id)
//        }
    }
}