package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R

@Composable
fun MovieDescription(overview: String) {
    Text(
        modifier = Modifier.padding(top = 20.dp, start = 30.dp, end = 30.dp),
        text = overview,
        color = Color.Gray,
        fontSize = 14.sp,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
    )
}