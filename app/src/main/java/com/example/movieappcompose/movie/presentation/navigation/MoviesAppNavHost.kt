package com.example.movieappcompose.movie.presentation.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.component.RetryItem
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.exploreRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.homeRoute
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.movieDetailsRoute
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import com.example.movieappcompose.util.NetworkUtils

@Composable
fun MoviesAppNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onEvent: (MovieUiEvent) -> Unit
) {
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = ScreenRoute.Home.route) {
        homeRoute(navController, innerPadding)
        exploreRoute(navController)
        movieDetailsRoute(navController)
    }
}