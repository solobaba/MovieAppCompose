package com.example.movieappcompose.movie.presentation.screens.searchScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    focusRequester: FocusRequester,
    focus: Boolean
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf(
            "Android development",
            "Jetpack compose"
        )
    }

    val keyboardController = LocalSoftwareKeyboardController.current

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            query = text,
            onQueryChange = {
                text = it
            },
            onSearch = {
                items.add(text)
                active = false
                text = ""
            },
            placeholder = {
                Text(text = stringResource(R.string.search_movies),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(Font(R.font.mulish_bold)),
                        color = DarkGray,
                        fontSize = 16.sp
                    ))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()) {
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
                inputFieldColors = TextFieldDefaults.colors(
                    focusedTextColor = DarkGray,
                    focusedContainerColor = DarkGray,
                    cursorColor = DarkGray)
            ),
            content = {
                items.forEach {
                    Row(
                        modifier = Modifier.padding(all = 14.dp)
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            imageVector = Icons.Default.History,
                            contentDescription = "History icon"
                        )
                        Text(text = it)
                    }
                }
            },
            active = active,
            onActiveChange = {
                active = it
            },
            tonalElevation = 0.dp
        )

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