package com.example.movieappcompose.ui.screen.detailScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
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
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.MovieDetail
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.util.shortToast
import com.example.movieappcompose.viewmodel.MovieDetailsViewModel
import com.google.gson.Gson
import kotlinx.coroutines.delay

@Composable
fun MovieDetailsScreen(
    movieID: Int,
    navController: NavController
) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val loading by viewModel.loading.collectAsState()

    //CircularIndeterminateProgressBar(isDisplayed = loading)

    viewModel.movieID = movieID

    val mContext = LocalContext.current
    mContext.shortToast(viewModel.movieID.toString())

    MovieDetailsContent(navController)
}

@Composable
fun MovieDetailsContent(navController: NavController) {
    val viewModel: MovieDetailsViewModel = viewModel()
    val movieDetails = viewModel.movieDetails.value
    Log.d("DetailsMovie", Gson().toJson(movieDetails))

    Box(modifier = Modifier.fillMaxSize()) {
        UpperSection(
            navController,
            movieDetails.backdrop_path,
            movieDetails.homepage,
            movieDetails.id
        )
    }
}