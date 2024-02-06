package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
fun MovieTitle(
    title: String
) {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 20.dp, start = 50.dp, end = 50.dp),
        text = title,
        color = Color.LightGray,
        fontFamily = FontFamily(Font(R.font.mulish_bold)),
        fontSize = 24.sp,
        maxLines = 2,
        textAlign = TextAlign.Center,
        lineHeight = 32.sp,
    )
}