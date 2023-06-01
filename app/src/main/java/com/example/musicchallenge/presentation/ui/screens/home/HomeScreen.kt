package com.example.musicchallenge.presentation.ui.screens.home

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.MusicCard
import com.example.musicchallenge.presentation.ui.screens.player.PlayerViewModel
import kotlinx.coroutines.CoroutineScope


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigateToPlayer: () -> Unit,
    playerViewModel: PlayerViewModel,
    drawerState: DrawerState,
    scope: CoroutineScope
) {

    val viewState by homeViewModel.state.collectAsState()
    val mainSongsList by homeViewModel.mainListSongs.collectAsState() // Collect the flow as a state
    val genres by homeViewModel.genres.collectAsState()
    val chart by homeViewModel.chart.collectAsState()
    val songs = chart?.tracks
    val selectedGenre = viewState.selectedGenre
    val songsByGenreList by homeViewModel.songsByGenreList.collectAsState()

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
            HomeHeader(homeViewModel = homeViewModel, scope = scope, drawerState = drawerState)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color(0xFFFFFFFF), //TODO
                modifier = Modifier.padding(all = 4.dp),
                text = "Trending right now",
                style = MaterialTheme.typography.headlineLarge
            )
            mainSongsList?.let { it1 ->
                SongList(
                    songs = it1,
                    navigateToPlayer = navigateToPlayer,
                    homeViewModel = homeViewModel, playerViewModel = playerViewModel,

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
            if (mainSongsList.isNotEmpty()) {
                SongsByGenreList(
                    songs=mainSongsList
                )
            }
        }
    }

}


@Composable
fun SongList(
    songs: List<Song>,
    navigateToPlayer: () -> Unit,
    homeViewModel: HomeViewModel,
    playerViewModel: PlayerViewModel
) {

    LazyRow {
        itemsIndexed(songs) { index: Int, song: Song ->
            MusicCard(
                song = song,
                onClick = { isRunning ->
                    if (!isRunning)
                        homeViewModel.onEvent(
                            HomeEvents.PlaySound(
                                isRunning = false,
                                playWhenReady = false,
                                idx = index,
                                song = song
                            )
                        )
                    navigateToPlayer()
                },
                homeViewModel = homeViewModel,

                backgroundColor = MaterialTheme.colorScheme.surface

            )
        }
    }
}



