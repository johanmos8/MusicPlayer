package com.example.musicchallenge.presentation.ui.screens.player

import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.musicchallenge.presentation.ui.PlayerButtons
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
    playerViewModel: PlayerViewModel
) {
    val context = LocalContext.current

    // Pasar el contexto al ViewModel
    LaunchedEffect(Unit) {
        playerViewModel.initialize(context)
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
                trackImageUrl = Uri.EMPTY,//musicState.currentSong.artworkUri,
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))
            Text(
                text = "text",//musicState.currentSong.title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = "Text2",//musicState.currentSong.album,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceSmall)
            )
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))
            Slider(
                modifier = Modifier.padding(horizontal = spacing.spaceMedium),
                value = Float.MIN_VALUE,
                onValueChange = { }
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            ) {
                Text(text = "current position")//currentPosition.asFormattedString())
                Spacer(modifier = Modifier.weight(1f))
                //Text(musicState.duration.asFormattedString())
            }
            Spacer(modifier = androidx.compose.ui.Modifier.height(spacing.spaceMediumLarge))
            Button(onClick = { playerViewModel.startPlayback("https://cdns-preview-7.dzcdn.net/stream/c-7ea59b3c9d85de02f9a8990946cb4ab2-8.mp3") }) {
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

