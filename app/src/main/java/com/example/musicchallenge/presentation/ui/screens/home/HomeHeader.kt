package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeHeader(
    homeViewModel: HomeViewModel,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    Row {
        MusicTopBar(
            homeViewModel = homeViewModel
        ) { scope.launch { drawerState.open() } }
    }
}