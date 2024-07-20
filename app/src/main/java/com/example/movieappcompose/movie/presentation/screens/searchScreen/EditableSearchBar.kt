package com.example.movieappcompose.movie.presentation.screens.searchScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieappcompose.R
import com.example.movieappcompose.theme.Gray600

@SuppressLint("UnrememberedMutableState")
@Composable
fun EditableSearchBar(
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    onValueChange: ((query: String) -> Unit)? = null
) {
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    val showHintState = derivedStateOf {
        searchQuery.isEmpty()
    }

    BasicTextField(
        value = searchQuery,
        onValueChange = {
            searchQuery= it
            if (onValueChange != null) {
                onValueChange(it)
            }
        },
        modifier = modifier.focusRequester(focusRequester)
            .background(DarkGray),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = FontFamily(Font(R.font.mulish_regular)),
            color = DarkGray, fontSize = 14.sp
        ),
        readOnly = false,
        cursorBrush = Brush.verticalGradient(
            0.00f to Color.Transparent,
            0.1f to Color.Transparent,
            0.1f to Color.White,
            0.90f to Color.White,
            0.90f to Color.Transparent,
            1.00f to Color.Transparent
        ),
    ) { innerTextField ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.large)
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = (24.7).dp, end = (17.78).dp)
                    .background(DarkGray),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.search),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(Font(R.font.mulish_bold)),
                        color = if (showHintState.value) Gray600 else DarkGray,
                        fontSize = 14.sp),
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    modifier = Modifier.size(16.dp),
                    tint = DarkGray
                    )
            }
        }
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(start = (24.7).dp, end = (17.78).dp)
                .background(Color.DarkGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            innerTextField()
        }
    }
}