package com.example.movieappcompose.movie.presentation.screens.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R

@Composable
fun RatingSection(
    modifier: Modifier = Modifier,
    popularity: Double,
    status: String?,
    releaseDate: String?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        MovieStatus(status)
        ReleasedDate(releaseDate)
        Popularity(popularity)
    }
}

@Composable
fun MovieStatus(status: String?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = status ?: "",
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.mulish_bold)),
                fontSize = 14.sp
            )
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "Status",
            color = Color(0xFF9C9C9C),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ReleasedDate(releaseDate: String?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = releaseDate ?: "",
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.mulish_bold)),
                fontSize = 14.sp
            )
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "Released date",
            color = Color(0xFF9C9C9C),
            fontSize = 12.sp
        )
    }
}

@Composable
fun Popularity(popularity: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = popularity.toString(),
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.mulish_bold)),
                fontSize = 14.sp
            )
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = "Popularity",
            color = Color(0xFF9C9C9C),
            fontSize = 12.sp
        )
    }
}