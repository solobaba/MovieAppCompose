package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.model.response.Genre

@Composable
fun Genres(genres: List<Genre?>) {
    LazyRow(contentPadding = PaddingValues(16.dp)) {
        itemsIndexed(genres) {_, genres ->
            GenreItems(genres)
        }
    }
}

@Composable
fun GenreItems(genres: Genre?) {
    Row(modifier = Modifier.padding(5.dp)) {
        Button(
            modifier = Modifier.padding(2.dp),
            onClick = {},
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            )) {
            Text(
                text = genres?.name ?: "",
                color = Color.DarkGray
                )
        }
    }
}