package com.example.movieappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieappcompose.theme.MovieAppComposeTheme
import com.example.movieappcompose.ui.navigation.BottomNavigation
import com.example.movieappcompose.ui.navigation.MoviesAppNavHost
import com.example.movieappcompose.ui.navigation.navGraphBuilder.ExploreMoviesArgs
import com.example.movieappcompose.ui.screen.ScreenRoute

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedRoute = mutableStateOf("explorerMoviesList/{${ExploreMoviesArgs.ID}}")

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            MovieAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxHeight(),
                    containerColor = MaterialTheme.colorScheme.primary,
                    bottomBar = {
                        if (navBackStackEntry?.destination?.route == ScreenRoute.Explore.route) {
                            BottomNavigation(
                                selectedRoute = selectedRoute.value,
                                onItemClick = { selectedRoute.value = it }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(
                            bottom = max(
                                a = 0.dp,
                                b = innerPadding.calculateBottomPadding() - 20.dp
                            ),
                        ),
                        content = {
                            MoviesAppNavHost(
                                navController,
                                innerPadding,
                                onClickButton = { }
                            )
                        },
                    )
                }
            }
        }
    }
}