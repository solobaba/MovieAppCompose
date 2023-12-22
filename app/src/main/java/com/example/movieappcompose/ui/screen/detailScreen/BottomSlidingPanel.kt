package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.model.response.Genre

@Composable
fun BottomSlidingPanel(
    genres: List<Genre>,
    title: String?,
    overview: String?,
    popularity: Double?,
    status: String?,
    releaseDate: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.55f)
            .background(Color.White)
            .offset(y = 32.dp)
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            RatingSection(modifier = Modifier.padding(PaddingValues(horizontal = 50.dp)),
                popularity, status, releaseDate)
        }
    }
}