package com.example.movieappcompose.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.movieappcompose.ui.navigation.navGraphBuilder.exploreRoute
import com.example.movieappcompose.ui.navigation.navGraphBuilder.homeRoute
import com.example.movieappcompose.ui.navigation.navGraphBuilder.movieDetailsRoute
import com.example.movieappcompose.ui.screen.ScreenRoute

@Composable
fun MoviesAppNavHost(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        homeRoute(navController, innerPadding)
        exploreRoute(navController)
        movieDetailsRoute(navController)

//        composable(ScreenRoute.Home.route) {
//            MoviesHome(navController) { navigationId ->
//                if (navigationId == 0 || navigationId == 1) {
//                    navController.navigate( "explorerMoviesList/$navigationId")
//                } else {
//                    navController.navigate( "movieDetails/$navigationId")
//                }
//            }
//        }

//        composable(ScreenRoute.Explore.route,
//            arguments = listOf(navArgument(name = ID) {
//                type = NavType.IntType
//            })) { navBackStackEntry ->
//            val id = navBackStackEntry.arguments?.getInt(ID)
//            ExploreMoviesList(navController, id)
//        }

//        composable(ScreenRoute.Details.route,
//            arguments = listOf(navArgument(name = "movies_id") {
//                type = NavType.IntType
//            })) { navBackStackEntry ->
//            val viewModel : MovieDetailsViewModel = viewModel()
//            MovieDetailsScreen(navController, viewModel.movieState.value)
//        }
    }
}