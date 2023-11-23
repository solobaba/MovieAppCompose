package com.example.movieappcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappcompose.ui.screen.ID
import com.example.movieappcompose.ui.screen.ScreenRoute
import com.example.movieappcompose.ui.screen.exploreScreen.ExploreMoviesList
import com.example.movieappcompose.ui.screen.homeScreen.MoviesHome

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
                type = NavType.IntType
            })) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(ID)
            ExploreMoviesList(navController, id)
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