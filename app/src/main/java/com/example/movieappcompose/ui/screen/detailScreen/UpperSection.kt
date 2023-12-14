package com.example.movieappcompose.ui.screen.detailScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson

@Composable
fun UpperSection(
    navController: NavController,
    backdropPath: String?,
    homepage: String?,
    id: Int?
) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val movieDetails = viewModel.movieDetails.value
    Log.d("DetailsMovie", Gson().toJson(movieDetails))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        AppBar(navController, id)
        ImageMovie(backdropPath, modifier = Modifier.align(Alignment.TopStart))
    }
}

@Composable
fun ImageMovie(backdropPath: String?, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(backdropPath),
        contentDescription = "movie_image",
        modifier = Modifier.fillMaxWidth()
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}