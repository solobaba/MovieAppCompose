package com.example.movieappcompose.ui.screen.detailScreen

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.util.longToast
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson

@Composable
fun UpperSection(
    navController: NavController,
    backdropPath: String?,
    homepage: String?,
    id: Int?,
    title: String
) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val movieDetails = viewModel.movieDetails.value
    Log.d("DetailsMovie", Gson().toJson(movieDetails))

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {
        ImageMovie(backdropPath, modifier = Modifier.align(Alignment.TopStart))
        AppBar(navController, id)
        PlayButton(
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    if (homepage == "" || homepage == null) {
                        context.longToast("$title trailer is not available")
                    } else {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(homepage)
                        )
                        startActivity(context, intent, null)
                    }
                }
        )
    }
}

@Composable
fun ImageMovie(backdropPath: String?, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(R.drawable.image_8),
        contentDescription = "movie_image",
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PlayButton(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_play),
        contentDescription = "Play_Icon",
        modifier = modifier
            .clip(CircleShape)
            .size(54.dp)
            .background(Color(0xFFFF5524))
            .padding(PaddingValues(16.dp))
    )
}