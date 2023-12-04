package com.example.movieappcompose.ui.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.viewmodel.FetchMoviesViewModel
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

@Composable
fun VerticalMovie(navController: NavHostController, navigationCallback: (Int) -> Unit) {
    //val scrollState = rememberLazyListState()
    val viewModel: FetchMoviesViewModel = viewModel()
    val horizontalMovies = viewModel.popularMoviesList.value

    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        items(horizontalMovies) { movie ->
            PopularMovies(navController, movie, navigationCallback)
        }
    }
}

@Composable
fun PopularMovies(navController: NavHostController, movie: Movie, navigationCallback: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { navigationCallback.invoke(movie.id) }
    ) {
        PopularMovieImage(movie.poster_path)
        MovieTitleOverview(
            movie.title,
            movie.overview,
            movie.vote_average,
            movie.release_date
        )
    }
}

@Composable
fun PopularMovieImage(posterPath: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .wrapContentSize()
            .clickable { }
    ) {
        AsyncImage(
            modifier = Modifier
                .height(130.dp)
                .width(100.dp),
            model = posterPath,
            contentScale = ContentScale.Crop,
            contentDescription = "",
            placeholder = painterResource(R.drawable.profile_picture)
        )
    }
}

@Composable
fun MovieTitleOverview(
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
            style = MaterialTheme.typography.bodySmall,
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