package com.example.musicchallenge.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicchallenge.presentation.ui.screens.Screens
import com.example.musicchallenge.presentation.ui.screens.favorite.FavoriteScreen
import com.example.musicchallenge.presentation.ui.screens.home.HomeScreen
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.screens.player.PlayerScreen
import com.example.musicchallenge.presentation.ui.screens.player.PlayerViewModel
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
fun MusicApp(
    homeViewModel: HomeViewModel,
    playerViewModel: PlayerViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // icons to mimic drawer destinations
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor =  Color(0xFF352547)) {
                FavoriteScreen(homeViewModel = homeViewModel)
            }
        },

        ) {

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
                    playerViewModel = playerViewModel,
                    drawerState = drawerState,
                    scope = scope
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
