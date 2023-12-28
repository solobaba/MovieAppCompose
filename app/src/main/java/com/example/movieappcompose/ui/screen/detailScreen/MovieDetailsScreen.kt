package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.Movie

@Composable
fun MovieDetailsScreen(navController: NavHostController, movies: Movie?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(18.dp)
                        .clickable { navController.navigateUp() },
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Expand row icon",
                    tint = Color(0xFFFFFFFF)
                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    model = R.drawable.profile_picture,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.profile_picture)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = movies?.title ?: "Default title",
                color = Color.DarkGray,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.mulish_bold)),
                fontSize = 18.sp
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
                    text = movies?.vote_average.toString() ?: "Default count",
                    color = Color(0xFF9C9C9C),
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = FontFamily(Font(R.font.mulish_regular)),
                    fontSize = 12.sp
                )
            }
            Text(
                modifier = Modifier.wrapContentSize(),
                text = "Description",
                color = Color.DarkGray,
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 3.dp),
                text = movies?.overview ?: "Default overview",
                color = Color(0xFF9C9C9C),
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

