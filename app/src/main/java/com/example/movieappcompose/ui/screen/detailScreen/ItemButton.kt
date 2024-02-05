package com.example.movieappcompose.ui.screen.detailScreen

import android.text.Layout.Alignment
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R
import com.example.movieappcompose.util.SpacerHorizontal4Dp

@Composable
fun ItemButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String,
    fontSize: TextUnit = 16.sp,
    backgroundColor: Color = Color(0xFFFF5524),
    textColor: Color = Color.Black,
    border: BorderStroke? = null,
    @DrawableRes iconResource: Int? = null,
    iconColor: Color = Color.Black.copy(alpha = .87f)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        border = border,
        modifier = Modifier
            .padding(top = 20.dp)
            .defaultMinSize(minWidth = 100.dp)
            .then(modifier)
    ) {
        iconResource?.let { resourceId ->
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "card icon",
                colorFilter = ColorFilter.tint(color = iconColor)
            )
            SpacerHorizontal4Dp()
        }
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
            fontFamily = FontFamily(Font(R.font.mulish_bold))
        )
    }
}