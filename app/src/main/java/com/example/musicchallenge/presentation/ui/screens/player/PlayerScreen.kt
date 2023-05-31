package com.example.musicchallenge.presentation.ui.screens.player

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.screens.player.components.PlayerImage
import com.example.musicchallenge.presentation.ui.theme.LocalSpacing
import com.example.musicchallenge.presentation.util.asFormattedString
import com.example.musicchallenge.presentation.util.convertToProgress


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

    val musicState by playerViewModel.musicState.collectAsState()
    val currentPosition = playerViewModel.currentPosition.collectAsState()
    val progress by animateFloatAsState(
        targetValue = convertToProgress(count = currentPosition.value, total = musicState.duration)
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
            .background(Color(0xFF2D2E37))

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
            trackImageUrl = musicState.currentSong?.album?.cover,
        )
        Log.d("PlayerVM", "$musicState.currentSong")
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        musicState.currentSong?.let {
            Text(
                text = it.title ?: "Unknown",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
        Text(
            text = musicState.currentSong?.artist?.name ?: "",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = spacing.spaceSmall)
        )
        Spacer(modifier = Modifier.height(spacing.spaceMediumLarge))
        PlayerButtons(
            modifier = Modifier.fillMaxWidth(),
            playWhenReady = musicState.playWhenReady,
            play = { playerViewModel.onEvent(PlayerEvent.Play) },
            pause = { playerViewModel.onEvent(PlayerEvent.Pause) },
            next = { playerViewModel.onEvent(PlayerEvent.SkipNext) },
            previous = { playerViewModel.onEvent(PlayerEvent.SkipPrevious) },
            repeat = { playerViewModel.onEvent(PlayerEvent.Repeat) },
            shuffle = { playerViewModel.onEvent(PlayerEvent.Shuffle) }
        )
        Spacer(modifier = Modifier.height(spacing.spaceExtraLarge))
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
            Text(currentPosition.value.asFormattedString())//"${musicState.duration}")//currentPosition.asFormattedString())
            Spacer(modifier = Modifier.weight(1f))
            musicState.currentSong?.duration?.asFormattedString()?.let { Text(it) }
        }
        Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))

    }
}

