package com.example.musicchallenge.presentation.ui.screens.player

import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicchallenge.presentation.ui.PlayerButtons
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.screens.player.components.PlayerHeader
import com.example.musicchallenge.presentation.ui.screens.player.components.PlayerImage
import com.example.musicchallenge.presentation.ui.theme.LocalSpacing
import com.example.musicchallenge.presentation.util.DynamicThemePrimaryColorsFromImage
import com.example.musicchallenge.presentation.util.contrastAgainst
import com.example.musicchallenge.presentation.util.rememberDominantColorState


@ExperimentalMaterial3Api
@Composable
fun PlayerScreen(
    onBackPressed: () -> Unit,
    addToPlayList: () -> Unit,
    more: () -> Unit,
    playerViewModel: PlayerViewModel,
    homeViewModel: HomeViewModel
) {
    val context = LocalContext.current
    val musicState by homeViewModel.state.collectAsState()
    val currentPosition by playerViewModel.currentPosition.collectAsState()
    val progress by animateFloatAsState(
        targetValue =     ((currentPosition * 100f) / 120 / 100f).takeIf(Float::isFinite) ?: 0f
    )
    // Pasar el contexto al ViewModel
    LaunchedEffect(Unit) {
        homeViewModel.initialize(context)
    }
    val spacing = LocalSpacing.current

    val surfaceColor = MaterialTheme.colorScheme.surface

    Column(
        modifier = Modifier
            .fillMaxSize()

        /* .verticalGradientScrim(
             color = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
             startYPercentage = 1f,
             endYPercentage = 0f
         )*/
    ) {
        Spacer(
            Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        //PlayerHeader(onBackPress = onBackPressed, addToPlayList = addToPlayList, more = more)
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))
        PlayerImage(
            modifier = Modifier
                .fillMaxWidth(),
            trackImageUrl = musicState.currentSong?.artist?.picture,
        )
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        musicState.currentSong?.let {
            Text(
                text = it.title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
            text = musicState.currentSong?.artist?.name?:"",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        Slider(
            modifier = Modifier.padding(horizontal = spacing.spaceMedium),
            value = progress,
            onValueChange = { }
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceMedium)
        ) {
            Text(text = "Duracion" )//"${musicState.duration}")//currentPosition.asFormattedString())
            Spacer(modifier = Modifier.weight(1f))
            Text("2")
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))
        Log.d("Ruta Archivo: ",""+musicState.currentSong?.preview )
        Button(onClick = { homeViewModel.startPlayback() }) {
            Text("Start Playback")
        }
        PlayerButtons(
            modifier = Modifier.fillMaxWidth(),
            /*playWhenReady = musicState.playWhenReady,
            play = { viewModel.onEvent(PlayerEvent.Play) },
            pause = { viewModel.onEvent(PlayerEvent.Pause) },
            replay10 = { },
            forward10 = { /*TODO*/ },
            next = { viewModel.onEvent(PlayerEvent.SkipNext) },
            previous = { viewModel.onEvent(PlayerEvent.SkipPrevious) }*/
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceExtraLarge))
    }
}

