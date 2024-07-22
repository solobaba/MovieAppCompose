package com.example.movieappcompose.movie.presentation.screens.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieappcompose.R
import com.example.movieappcompose.movie.presentation.component.SearchToolbar

@Composable
fun SearchLayout(
    navController: NavController,
    focus: Boolean
) {
    var showSearchDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        val focusRequester = remember {
            FocusRequester()
        }

        SearchToolbar(
            modifier = Modifier
                .fillMaxWidth()
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
        SearchScreen(navController, focusRequester, focus)
    }
}

@Composable
fun SearchScreen(
    navController: NavController,
    focusRequester: FocusRequester,
    focus: Boolean
) {
    Spacer(modifier = Modifier.height(36.dp))

    SearchScreen(
        modifier = Modifier.fillMaxWidth()
            .height(42.dp)
            .padding(20.dp)
            .background(Color.Red),
        searchQuery = null,
        focusRequester = focusRequester
    ) {
    }

//    EditableSearchBar(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(42.dp)
//            .padding(29.dp)
//            .background(Color.DarkGray),
//        focusRequester = focusRequester
//    ) {
//    }

    if (focus)
        LaunchedEffect(key1 = true, block = { focusRequester.requestFocus() })

    Spacer(modifier = Modifier.height(24.dp))
}