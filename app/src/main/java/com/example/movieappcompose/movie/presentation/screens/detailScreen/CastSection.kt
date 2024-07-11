package com.example.movieappcompose.movie.presentation.screens.detailScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CastSection(@DrawableRes actorsImages: List<Int>, contentPadding: PaddingValues = PaddingValues(0.dp)) {
    LazyRow(contentPadding = contentPadding, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(actorsImages) { imageId ->
            Image(
                painter = painterResource(id = imageId),
                contentDescription = "Actors Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape))
        }
    }
}