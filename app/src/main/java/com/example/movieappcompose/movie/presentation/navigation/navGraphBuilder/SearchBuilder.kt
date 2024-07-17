package com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.component.Toolbar
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import com.example.movieappcompose.movie.presentation.screens.homeScreens.MoviesHome
import com.example.movieappcompose.util.shortToast

private const val ROUTE = "search"

fun NavGraphBuilder.searchRoute(navController: NavController, innerPadding: PaddingValues) {
    composable(ScreenRoute.Search.route) {
        Box(modifier = Modifier.padding(innerPadding)) {
            SearchLayout(navController = navController)
        }
    }
}

@Composable
fun SearchLayout(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Toolbar(
            title = stringResource(R.string.search),
            icon = rememberImagePainter(R.drawable.ic_baseline_arrow_black_back_24)) {
            navController.popBackStack()
        }
        SearchScreen(navController)
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        context.shortToast("Search screen view")
    }
}