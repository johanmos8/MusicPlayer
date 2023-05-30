package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.models.Track
import com.example.musicchallenge.presentation.ui.MusicCard
import com.example.musicchallenge.presentation.ui.screens.player.PlayerViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToPlayer: () -> Unit,
    playerViewModel: PlayerViewModel
) {

    val viewState by homeViewModel.state.collectAsState()
    //val songs by homeViewModel.songs.collectAsState() // Collect the flow as a state
    val genres by homeViewModel.genres.collectAsState()
    val chart by homeViewModel.chart.collectAsState()
    val songs = chart?.tracks
    val selectedGenre = viewState.selectedGenre


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(0xFF352547) //TODO MaterialTheme.colorScheme.tertiary
    ) {
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            //TODO("Menu hamburguesa")
            SearchBarRow(homeViewModel)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color(0xFFFFFFFF), //TODO
                modifier = Modifier.padding(all = 4.dp),
                text = "Trending right now",
                style = MaterialTheme.typography.headlineLarge
            )
            songs?.data?.let { it1 ->
                SongList(
                    songs = it1,
                    navigateToPlayer = navigateToPlayer,
                    homeViewModel = homeViewModel, playerViewModel = playerViewModel
                )
            }

            if (genres.isNotEmpty()) {
                GenresTabs(
                    modifier = Modifier.background(color = Color(0xFF352547)),
                    genres = genres,
                    selectedGenre = selectedGenre,
                    onGenreSelected = homeViewModel::onGenreSelected
                )
            }
        }
    }

}


@Composable
fun SongList(
    songs: List<Track>,
    navigateToPlayer: () -> Unit,
    homeViewModel: HomeViewModel,
    playerViewModel: PlayerViewModel
) {

    LazyRow {
        itemsIndexed(songs) { index: Int, song: Track ->
            MusicCard(
                song = song,
                onClick = {
                    homeViewModel.onSongSelected(song)
                    navigateToPlayer()
                },
                homeViewModel = homeViewModel,
                playPauseTrack = { isRunning, playWhenReady ->
                    homeViewModel.onEvent(
                        HomeEvents.PlaySound(
                            isRunning,
                            playWhenReady,
                            index
                        )
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.surface

            )
        }
    }
}



