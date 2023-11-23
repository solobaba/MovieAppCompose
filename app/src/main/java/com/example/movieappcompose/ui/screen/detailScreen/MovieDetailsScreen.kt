package com.example.movieappcompose.ui.screen.detailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R

@Composable
fun MovieDetails() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentSize()
            ) {
                Icon(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(18.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Expand row icon",
                    tint = Color(0xFFFFFFFF)
                )
//                Image(
//                    painter = rememberImagePainter(R.drawable.ic_baseline_arrow_back_24),
//                    modifier = Modifier
//                        .padding(horizontal = 12.dp)
//                        .size(20.dp)
//                        .clickable(onClick = {  }),
//                    contentDescription = "",
//                )
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    model = R.drawable.profile_picture,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.profile_picture)
                )
            }
        }


    }
}