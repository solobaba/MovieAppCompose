package com.example.movieappcompose.movie.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.event.MovieUiEvent
import com.example.movieappcompose.movie.presentation.screens.ScreenRoute
import kotlin.math.truncate
import kotlin.reflect.KFunction1

@Composable
fun BottomNavigation(
    navController: NavController,
    selectedRoute: String,
    onItemClick: (String) -> Unit,
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

    val screens = listOf(
        ScreenRoute.Home,
        ScreenRoute.Search,
        ScreenRoute.Explore
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items.forEachIndexed { index, screen ->
                NavigationBarItem(selected = selected.intValue == index, onClick = {
                    selected.intValue = index
                    when (selected.intValue) {
                        0 -> {
                            onEvent(MovieUiEvent.Navigate)
                            navController.popBackStack()
                            navController.navigate(ScreenRoute.Home.route)
                        }
                    }
                 }, icon = {
                     Icon(
                         painterResource(id = screen.icon),
                         contentDescription = screen.title,
                         tint = Color.White
                     )
                }, label = {
                    Text(
                        text = screen.title,
                        color = Color.White )
                }, alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(Color(0xFFFF5524)))

//                val currentItem = screen.route == selectedRoute
//                val itemBackGroundColor = if (currentItem) Color(0xFFFF5524) else Color.Transparent
//                val itemColor = if (currentItem) Color.White.copy(alpha = .87f) else Color.Black.copy(alpha = .87f)
//
//                Button(
//                    onClick = { if (!currentItem) onItemClick(screen.route) },
//                    colors = ButtonDefaults.buttonColors(itemBackGroundColor),
//                    shape = CircleShape,
//                    modifier = Modifier
//                        .height(64.dp)
//                        .aspectRatio(1f)
//                ) {
//                    screen.imageIconRes?.let { resId ->
//                        Image(
//                            colorFilter = ColorFilter.tint(color = itemColor),
//                            painter = painterResource(id = resId),
//                            contentDescription = "image",
//                            modifier = Modifier
//                                .size(24.dp)
//                                .scale(1.4f)
//                        )
//                    }
//                }
            }
        }
    }
}

data class BottomItem(
    var title: String,
    @DrawableRes var icon: Int
)