package com.example.movieappcompose.ui.screen

import com.example.movieappcompose.R

const val ID = "id"
sealed class ScreenRoute(val route: String) {
    object Home: ScreenRoute(route = "moviesHome")
    object Explore: ScreenRoute(route = "explorerMoviesList/{$ID}")
    object Details: ScreenRoute(route = "movieDetails/{movies_id}")
}