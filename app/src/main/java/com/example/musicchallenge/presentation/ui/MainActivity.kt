package com.example.musicchallenge.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicchallenge.presentation.ui.screens.Screens
import com.example.musicchallenge.presentation.ui.screens.home.HomeScreen
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.screens.player.PlayerScreen
import com.example.musicchallenge.presentation.ui.screens.player.PlayerViewModel
import com.example.musicchallenge.presentation.ui.theme.AppTheme
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
                    composable(route = Screens.Home.route) { backStackEntry ->
                        HomeScreen(
                            navigateToPlayer = {
                                navController.navigate(
                                    Screens.Player.route
                                )
                            },
                            homeViewModel = homeViewModel,
                            playerViewModel = playerViewModel
                        )

                    }
                    composable(
                        route = Screens.Player.route,

                        ) {
                        PlayerScreen(
                            onBackPressed = { navController.popBackStack() },
                            addToPlayList = { /*TODO*/ },
                            more = { /*TODO*/ },
                            playerViewModel = playerViewModel,
                            homeViewModel = homeViewModel
                        )


                    }


                }


            }
        }
    }
}
