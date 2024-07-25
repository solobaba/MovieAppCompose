package com.example.movieappcompose.movie.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.exploreRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.homeRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.movieDetailsRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.searchRoute
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute

@Composable
fun MoviesAppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onEvent: (MovieUiEvent) -> Unit
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        homeRoute(navController, innerPadding)
        searchRoute(navController, innerPadding)
        exploreRoute(navController)
        movieDetailsRoute(navController)
    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        if (!NetworkUtils.isNetworkAvailable(context)) {
//            RetryItem(
//                modifier = Modifier
//                    .width(90.dp)
//                    .height(40.dp)
//                    .clickable {
//                        navController.navigate(ScreenRoute.Home.route)
//                    },
//                message = stringResource(id = R.string.check_your_internet_connection),
//                onClick = {
//                    onEvent(MovieUiEvent.Navigate)
//                    navController.popBackStack()
//                    navController.navigate(ScreenRoute.Home.route)
//                }
//            )
//        } else {
//            NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
//                homeRoute(navController, innerPadding)
//                exploreRoute(navController)
//                movieDetailsRoute(navController)
//            }
//        }
//    }
}