package com.example.movieappcompose.ui.navigation

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movieappcompose.ui.screen.ScreenRoute

@Composable
fun BottomNavigation(
    selectedRoute: String,
    onItemClick: (String) -> Unit
) {
    val screens = listOf(
        ScreenRoute.Home,
        ScreenRoute.Search,
        ScreenRoute.Explore
    )

    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        screens.forEach { screen ->
            val currentItem = screen.route == selectedRoute
            val itemBackGroundColor = if (currentItem) Color(0xFFFF5524) else Color.Transparent
            val itemColor = if (currentItem) Color.White.copy(alpha = .87f) else Color.Black.copy(alpha = .87f)

            Button(
                onClick = { if (!currentItem) onItemClick(screen.route) },
                colors = ButtonDefaults.buttonColors(itemBackGroundColor),
                shape = CircleShape,
                modifier = Modifier
                    .height(64.dp)
                    .aspectRatio(1f)
            ) {
                screen.imageIconRes?.let { resId ->
                    Image(
                        colorFilter = ColorFilter.tint(color = itemColor),
                        painter = painterResource(id = resId),
                        contentDescription = "image",
                        modifier = Modifier.size(24.dp).scale(1.4f)
                    )
                }
            }
        }
    }
}