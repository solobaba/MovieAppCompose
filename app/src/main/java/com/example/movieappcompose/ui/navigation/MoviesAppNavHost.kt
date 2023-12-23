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
    }
}