package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieappcompose.R

@Composable
fun AppBar(navController: NavController, id: Int?) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .offset(y = 24.dp)
            .padding(PaddingValues(horizontal = 16.dp))
    ) {
        CloseIcon(navController)
        MovieIDChip(id)
    }
}

@Composable
fun CloseIcon(navController: NavController) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "close_icon",
            modifier = Modifier
                .sizeIn(
                    maxWidth = Dp.Unspecified,
                    maxHeight = Dp.Unspecified
                )
                .padding(PaddingValues(8.dp))
                .clickable { navController.popBackStack() },
            colorFilter = ColorFilter.tint(Color(0xFFB5B5B5))
        )
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .blur(16.dp)
                .background(Color.LightGray.copy(alpha = .4f))
        )
    }
}

@Composable
fun MovieIDChip(id: Int?) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(CircleShape)
    ) {
        MovieID(
            id,
            modifier = Modifier
                .padding(PaddingValues(vertical = 5.dp, horizontal = 8.dp)),
            textColor = Color.White.copy(alpha = .7f)
        )
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .blur(16.dp)
                .background(Color.LightGray.copy(alpha = .4f))
        )
    }
}

@Composable
fun MovieID(id: Int?, modifier: Modifier, textColor: Color) {
    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_clock),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFFB5B5B5))
        )
        Text(
            text = id.toString(),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.mulish_regular)),
            color = textColor,
        )
    }
}
