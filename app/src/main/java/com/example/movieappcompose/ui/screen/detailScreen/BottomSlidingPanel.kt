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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.R
import com.example.movieappcompose.model.response.Genre

@Composable
fun BottomSlidingPanel(
    genres: List<Genre?>,
    title: String,
    overview: String?,
    popularity: Double,
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            RatingSection(modifier = Modifier.padding(PaddingValues(horizontal = 50.dp)),
                popularity, status, releaseDate)
            MovieTitle(title)
            Genres(genres)
            CastSection(
                actorsImages = listOf(
                    R.drawable.image_1,
                    R.drawable.image_2,
                    R.drawable.image_3,
                    R.drawable.image_4,
                    R.drawable.image_5,
                    R.drawable.image_6,
                    R.drawable.image_7,
                    R.drawable.image_8,
                    R.drawable.image_9
                ),
                contentPadding = PaddingValues(horizontal = 16.dp)
            )
            MovieDescription(overview ?: "")
            ItemButton(
                onClick = {},
                text = stringResource(R.string.booking),
                textColor = Color.White,
                iconResource = R.drawable.ic_card, iconColor = Color.White.copy(alpha = .87f),
                modifier = Modifier.padding(bottom = 32.dp + 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}