package com.example.movieappcompose.movie.presentation.screens.searchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.component.SearchToolbar
import com.example.movieappcompose.movie.presentation.component.Toolbar

@Composable
fun SearchLayout(navController: NavController) {

    var showSearchDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        SearchToolbar(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
                .padding(horizontal = 20.dp),
            title = stringResource(R.string.search),
            icon = rememberImagePainter(R.drawable.ic_baseline_arrow_black_back_24),
            searchIcon = painterResource(id = R.drawable.ic_info_circle),
            iconClick = {
                showSearchDialog = true
            }
        ) {
            navController.popBackStack()
        }
        if (showSearchDialog)
            SearchDialog {
                showSearchDialog = false
            }
        SearchScreen(navController)
    }
}

@Composable
fun SearchDialog(onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = { 
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.okay))
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

@Composable
fun SearchScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {}
}