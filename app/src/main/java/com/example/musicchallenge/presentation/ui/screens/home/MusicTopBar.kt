package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MusicTopBar(
  homeViewModel: HomeViewModel,
  onclickDrawer: () -> Unit
) {
  TopAppBar(
    title = {SearchBarRow(homeViewModel = homeViewModel)},
    modifier = Modifier.padding(8.dp),
    navigationIcon = {
      IconButton(onClick = onclickDrawer) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
      }
    }
  )
}