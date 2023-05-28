package com.example.musicchallenge.presentation.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.MusicCard


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToPlayer: () -> Unit,
) {

    val viewState by homeViewModel.state.collectAsState()
    val songs by homeViewModel.songs.collectAsState() // Collect the flow as a state
    val genres by homeViewModel.genres.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        tonalElevation = 5.dp,
    ) {
        Surface(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(15.dp)),
            color = Color(0xFF352547) //TODO MaterialTheme.colorScheme.tertiary
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                SearchBarRow(homeViewModel)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    color = Color(0xFFFFFFFF), //TODO
                    modifier = Modifier.padding(all = 4.dp),
                    text = "Trending right now",
                    style = MaterialTheme.typography.headlineLarge
                )
                if (songs.isNotEmpty()) {
                    SongList(songs, navigateToPlayer, homeViewModel)
                }
                Log.d("genres", "" + genres)
                GenresTabs(genres = genres, selectedGenre = null, onGenreSelected = {})
            }
        }
    }
}


@Composable
fun SongList(songs: List<Song>, navigateToPlayer: () -> Unit, homeViewModel: HomeViewModel) {
    LazyRow {
        items(songs) { song ->
            MusicCard(song = song,
                onClick = { isRunning ->
                if (!isRunning)
                    homeViewModel.onEvent(
                        HomeEvents.PlaySound(
                            isRunning = false,
                            playWhenReady = false,
                            idx = 0
                        )
                    )
                navigateToPlayer()
                })
        }
    }
}



