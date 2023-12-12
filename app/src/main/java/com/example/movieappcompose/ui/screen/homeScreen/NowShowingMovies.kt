package com.example.movieappcompose.ui.screen.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.Movie
import com.example.movieappcompose.ui.component.CircularIndeterminateProgressBar
import com.example.movieappcompose.ui.navigation.navGraphBuilder.navigateToDetailScreen
import com.example.movieappcompose.util.toJson
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
    val moviesString = movie.toJson()
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .width(150.dp)
            .wrapContentSize()
            .padding(10.dp)
            .clickable { navController.navigateToDetailScreen(movie.id) }
    ) {
        MovieImage(navController, movie.id, movie.poster_path ?: "")
        MovieTitle(movie.title)
        MovieRate(movie.vote_average)
    }
}

@Composable
fun MovieImage(navController: NavController, title: Int, posterPath: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .wrapContentSize()
            .clickable { navController.navigateToDetailScreen(title) }
    ) {
//        AsyncImage(
//            modifier = Modifier
//                .height(170.dp)
//                .fillMaxWidth(),
//            model = posterPath,
//            contentScale = ContentScale.Crop,
//            contentDescription = "",
//            placeholder = painterResource(R.drawable.profile_picture)
//        )
        
        Image(
            painter = rememberImagePainter(posterPath),
            //painter = rememberAsyncImagePainter(posterPath),
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
    }
}

@Composable
fun MovieTitle(title: String?) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        text = title ?: "Default title",
        color = Color.DarkGray,
        textAlign = TextAlign.Start,
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.mulish_bold))
    )
}

@Composable
fun MovieRate(voteAverage: Double?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(18.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = "Expand row icon",
                tint = Color(0xFFFFC319) //Color.Yellow
            )
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 5.dp)
                    .align(Alignment.CenterVertically),
                text = voteAverage.toString() ?: "Default count",
                color = Color(0xFF9C9C9C),
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.mulish_regular)),
                fontSize = 12.sp
            )
        }
}