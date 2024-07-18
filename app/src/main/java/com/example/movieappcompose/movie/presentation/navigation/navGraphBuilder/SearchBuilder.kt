package com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import com.example.movieappcompose.movie.presentation.screens.searchScreen.SearchLayout

private const val ROUTE = "search"

fun NavGraphBuilder.searchRoute(navController: NavController, innerPadding: PaddingValues) {
    composable(ScreenRoute.Search.route) {
        Box(modifier = Modifier.padding(innerPadding)) {
            SearchLayout(navController = navController)
        }
    }
}