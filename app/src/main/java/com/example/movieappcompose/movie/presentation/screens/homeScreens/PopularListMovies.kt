package com.example.movieappcompose.movie.presentation.screens.homeScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.data.remote.Constants
import com.example.movieappcompose.movie.domain.model.MovieList
import com.example.movieappcompose.movie.presentation.state.MovieState
import com.example.movieappcompose.movie.presentation.viewmodel.MovieListViewmodel
import com.example.movieappcompose.movie.presentation.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.movie.presentation.navigation.navGraphBuilder.navigateToDetailScreen

@Composable
fun PopularMovieList(navController: NavController) {
    val viewmodel = hiltViewModel<MovieListViewmodel>()
    val movieState = viewmodel.movieState.collectAsState().value
    val loading = viewmodel.loading.collectAsState().value

    PopularMovieLayout(
        navController = navController,
        movieState = movieState,
        loading = loading
    )
}

@Composable
fun PopularMovieLayout(
    navController: NavController,
    movieState: MovieState,
    loading: Boolean
) {
    Box(
        modifier = Modifier.fillMaxHeight()
    ) {
        CircularIndeterminateProgressBar(isDisplayed = loading)

        LazyColumn(contentPadding = PaddingValues(5.dp)) {
            items(movieState.popularMovieList.size) { movie ->
                PopularListMovies(navController, movieState.popularMovieList[movie])
            }
        }
    }
}

@Composable
fun PopularListMovies(navController: NavController, movieList: MovieList) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navController.navigateToDetailScreen(movieList.id ?: 0) }
    ) {
        PopularMoviesImage(navController, movieList)
        MoviesTitleOverview(
            movieList.title,
            movieList.overview,
            movieList.vote_average,
            movieList.release_date
        )
    }
}

@Composable
fun PopularMoviesImage(navController: NavController, movie: MovieList) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .wrapContentSize()
            .clickable { navController.navigateToDetailScreen(movie.id ?: 0) }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(130.dp)
                .width(100.dp),
            model = (Constants.IMAGE_BASE_URL + movie.backdrop_path) ?: "", //R.drawable.image_1,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            placeholder = painterResource(R.drawable.profile_picture)
        )
    }
}

@Composable
fun MoviesTitleOverview(
    title: String?,
    overview: String?,
    voteAverage: Double?,
    releaseDate: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = title ?: "Default title",
            color = Color.DarkGray,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.mulish_bold)),
            fontSize = 14.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            text = overview ?: "Default overview",
            color = Color(0xFF9C9C9C),
            textAlign = TextAlign.Start,
            fontSize = 12.sp,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 4
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(18.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Expand row icon",
                tint = Color(0xFFFFC319)
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically),
                text = voteAverage.toString() ?: "Default count",
                color = Color(0xFF9C9C9C), //Color.DarkGray,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.mulish_regular)),
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        ) {
            Image(
                painter = rememberImagePainter(R.drawable.ic_baseline_access_time_24),
                modifier = Modifier
                    .wrapContentSize()
                    .size(15.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically),
                text = releaseDate ?: "Default date",
                color = Color(0xFF9C9C9C), //Color.DarkGray,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.mulish_regular)),
                fontSize = 12.sp
            )
        }
    }
}