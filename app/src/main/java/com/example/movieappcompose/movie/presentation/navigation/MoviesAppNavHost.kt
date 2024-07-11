package com.example.movieappcompose.movie.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.exploreRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.homeRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.movieDetailsRoute
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute

@Composable
fun MoviesAppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onClickButton: () -> Unit
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        homeRoute(navController, innerPadding)
        exploreRoute(navController)
        movieDetailsRoute(navController)
    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        if (!NetworkUtils.isNetworkAvailable(context)) {
//            //context.shortToast("Network not available, please check your internet connection")
//
//            RetryItem(
//                modifier = Modifier
//                    .width(90.dp)
//                    .height(40.dp)
//                    .clickable {
//                        navController.navigate(ScreenRoute.Home.route)
//                    },
//                message = stringResource(id = R.string.check_your_internet_connection),
//                onClick = onClickButton
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