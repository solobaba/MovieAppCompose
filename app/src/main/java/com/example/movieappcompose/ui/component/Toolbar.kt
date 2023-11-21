package com.example.movieappcompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Icon(
                imageVector = icon,
                "content description",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
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