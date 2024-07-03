package com.example.movieappcompose.ui.screen

import androidx.annotation.DrawableRes
import com.example.movieappcompose.R

const val ID = "id"
sealed class ScreenRoute(
    val route: String,
    @DrawableRes val imageIconRes: Int? = null
) {
    object Home: ScreenRoute(route = "moviesHome", imageIconRes = R.drawable.ic_video_play)
    object Explore: ScreenRoute(route = "explorerMoviesList/{$ID}", imageIconRes = R.drawable.ic_ticket)
    object Details: ScreenRoute(route = "movieDetails/{movies_id}")
    object Search: ScreenRoute(route = "search", imageIconRes = R.drawable.ic_search)
}