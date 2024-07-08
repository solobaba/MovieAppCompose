package com.example.movieappcompose.ui.screen.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.data.remote.Constants
import com.example.movieappcompose.movie.data.remote.response.Movie
import com.example.movieappcompose.movie.presentation.screens.MovieImage
import com.example.movieappcompose.movie.presentation.screens.MovieRate
import com.example.movieappcompose.movie.presentation.screens.MovieTitle
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.ui.navigation.navGraphBuilder.navigateToDetailScreen
import com.example.movieappcompose.viewmodel.FetchMoviesViewModel

@Composable
fun HorizontalMovieList(navController: NavController) {
    val viewModel: FetchMoviesViewModel = viewModel()
    val horizontalMovies = viewModel.voteMoviesList.value
    val loading by viewModel._loading.collectAsState()

    Box(modifier = Modifier.fillMaxWidth()) {
        CircularIndeterminateProgressBar(isDisplayed = loading)

        LazyRow(contentPadding = PaddingValues(10.dp)) {
            itemsIndexed(horizontalMovies) { _, movie ->
                VoteCountMovieList(navController, movie)
            }
        }
    }
}

@Composable
fun VoteCountMovieList(navController: NavController, movie: Movie) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .wrapContentSize()
            .padding(10.dp)
            .clickable { navController.navigateToDetailScreen(movie.id ?: 0) }
    ) {
        MovieImage(navController, movie.id ?: 0, movie.backdrop_path ?: "")
        MovieTitle(movie.title)
        MovieRate(movie.vote_average)
    }
}