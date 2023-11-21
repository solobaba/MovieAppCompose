package com.example.movieappcompose.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.viewmodel.FetchMoviesViewModel

@Composable
fun MoviesHomeList() {
    nowShowing()
    horizontalMovieList()
}

@Composable
fun nowShowing() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(15.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.CenterVertically)) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Now Showing",
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                textAlign = TextAlign.Center
            )
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterEnd),
                onClick = { },
                border = BorderStroke(
                    width = 1.dp,
                    color = Color.LightGray
                )
            ) {
                Text(
                    text = "See more",
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
fun horizontalMovieList() {
    val viewModel: FetchMoviesViewModel = viewModel()
    val horizontalMovies = viewModel.voteMoviesList.value

    LazyColumn(contentPadding = PaddingValues(10.dp)) {
        items(horizontalMovies) { movie ->
            VoteCountMovieList(movie)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun VoteCountMovieList(movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier
                .wrapContentWidth()
                .clickable { }
        ) {
            Image(
                painter = rememberImagePainter(movie.poster_path),
                modifier = Modifier
                    .size(200.dp),
                contentDescription = "Horizontal Image"
            )
        }
        Text(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 20.dp),
            text = movie.title ?: "Default title",
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.headlineMedium
        )
        Row {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Expand row icon",
                modifier = Modifier.padding(16.dp),
                tint = Color.Yellow
            )
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 20.dp),
                text = movie.vote_average.toString() ?: "Default count",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
