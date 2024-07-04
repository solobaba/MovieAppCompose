package com.example.movieappcompose.ui.screen.homeScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.ui.component.Toolbar
import com.example.movieappcompose.ui.navigation.navGraphBuilder.navigateToExploreScreen
import com.example.movieappcompose.util.NetworkUtils

@Composable
fun MoviesHome(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Toolbar(
            title = stringResource(R.string.app_name),
            icon = rememberImagePainter(R.drawable.ic_baseline_movie_filter_24)
        ) {
        }
        MoviesHomeList(navController)
    }
}

@Composable
fun MoviesHomeList(navController: NavController) {
    NowShowingMoviesList(navController)
    PopularMoviesList(navController)
}

@Composable
fun NowShowingMoviesList(
    navController: NavController
) {
    NowShowingHeader(navController)
    HorizontalMovieList(navController)
}

@Composable
fun NowShowingHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = stringResource(R.string.now_showing),
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterEnd),
                onClick = { navController.navigateToExploreScreen("0") },
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                )
            ) {
                Text(
                    text = stringResource(R.string.see_more),
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun PopularMoviesList(
    navController: NavController
) {
    PopularMoviesHeader(navController)
    VerticalMovie(navController)
}

@Composable
fun PopularMoviesHeader(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = stringResource(R.string.popular),
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterEnd),
                onClick = { navController.navigateToExploreScreen("1") },
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                )
            ) {
                Text(
                    text = stringResource(R.string.see_more),
                    color = Color.DarkGray
                )
            }
        }
    }
}