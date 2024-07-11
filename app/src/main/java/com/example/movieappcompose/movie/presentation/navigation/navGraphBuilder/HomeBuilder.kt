package com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieappcompose.movie.presentation.screens.homeScreens.MoviesHome

private const val ROUTE = "moviesHome"

fun NavGraphBuilder.homeRoute(navController: NavController, innerPadding: PaddingValues) {
    composable(ROUTE) {
        Box(modifier = Modifier.padding(innerPadding)) {
            MoviesHome(navController = navController)
        }
    }
}