package com.example.musicchallenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musicchallenge.presentation.ui.screens.Screens
import com.example.musicchallenge.presentation.ui.screens.home.HomeScreen
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.theme.AppTheme
import com.example.musicchallenge.presentation.ui.screens.player.PlayerScreen
import com.example.musicchallenge.presentation.ui.screens.player.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val playerViewModel: PlayerViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = Screens.Home.route) {
                        HomeScreen(
                            navigateToPlayer = {
                                navController.navigate(
                                    Screens.Player.route
                                )
                            },
                            homeViewModel = homeViewModel
                        )

                    }
                    composable(
                        route = Screens.Player.route,

                        ) {
                        PlayerScreen(
                            onBackPressed = { },
                            addToPlayList = { /*TODO*/ },
                            more = { /*TODO*/ },
                            playerViewModel = playerViewModel
                        )


                    }


                }


            }
        }
    }
}
