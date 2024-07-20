package com.example.movieappcompose.movie.presentation.screens.searchScreen

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R

@Composable
fun SearchDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.okay),
                    color = Color.DarkGray)
            }
        },
        title = {
            Text(text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.bodyMedium.copy(
                    Color.DarkGray, fontFamily = FontFamily(
                        Font(R.font.merriweather_black)
                    ),fontSize = 16.sp))
        },
        text = {
            Text(text = stringResource(id = R.string.search_text),
                style = MaterialTheme.typography.bodyLarge.copy(
                    Color.DarkGray, fontSize = 14.sp))
        }
    )
}