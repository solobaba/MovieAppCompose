package com.example.movieappcompose.movie.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import com.example.movieappcompose.theme.Black800
import com.example.movieappcompose.theme.BlueLight
import com.example.movieappcompose.theme.Gray500

@Composable
fun BottomNav(
    navController: NavController,
    onEvent: (MovieUiEvent) -> Unit
) {
    val items = listOf(
        BottomItem(
            title = stringResource(R.string.home),
            icon = R.drawable.ic_ticket
        ), BottomItem(
            title = stringResource(R.string.search),
            icon = R.drawable.ic_search
        ), BottomItem(
            title = stringResource(R.string.watch_list),
            icon = R.drawable.ic_video_play
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(78.dp),
        backgroundColor = Black800
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(BlueLight))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                items.forEachIndexed { index, bottomItem ->
                    BottomNavigationItem(
                        selected = selected.intValue == index,
                        selectedContentColor = BlueLight,
                        unselectedContentColor = Gray500,
                        onClick = {
                            selected.intValue = index
                            when (selected.intValue) {
                                0 -> {
                                    onEvent(MovieUiEvent.Navigate)
                                    navController.popBackStack()
                                    navController.navigate(ScreenRoute.Home.route)
                                }
                                1 -> {
                                    navController.navigate(ScreenRoute.Search.route)
                                }
                            }
                        }, icon = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .width(21.dp)
                                        .height(24.dp),
                                    painter = rememberImagePainter(bottomItem.icon),
                                    contentDescription = bottomItem.title,
                                    tint = Color.White
                                )
                                Text(
                                    text = bottomItem.title,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodySmall,
                                    maxLines = 1
                                )
                            }
                        })
                }
            }
        }
    }
}

data class BottomItem(
    var title: String,
    @DrawableRes var icon: Int
)