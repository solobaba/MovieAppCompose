package com.example.movieappcompose.movie.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import com.example.movieappcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String,
    icon: AsyncImagePainter,
    iconClickAction: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Image(
                painter = icon,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(20.dp)
                    .clickable(onClick = { iconClickAction.invoke() }),
                contentDescription = "",
            )
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = Color.DarkGray,
            titleContentColor = Color.DarkGray,
        )
    )
}

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    title: String,
    icon: AsyncImagePainter,
    searchIcon: Painter? = null,
    iconClick: (() -> Unit)? = null,
    iconWidth: Dp = 24.dp,
    iconClickAction: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = icon,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(onClick = { iconClickAction.invoke() }),
                contentDescription = "",
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.merriweather_black)),
                fontSize = 18.sp,
                color = Color.DarkGray
            )

            if (searchIcon == null) {
                Spacer(modifier = Modifier.size(20.dp))
            } else {
                IconButton(
                    onClick = { iconClick?.invoke() },
                    modifier = Modifier
                        .width(iconWidth)
                        .fillMaxHeight()
                ) {
                    Icon(
                        painter = searchIcon,
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}