package com.example.musicchallenge.presentation.ui.screens.player.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.R
import com.example.musicchallenge.presentation.util.AppIcons


@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    playWhenReady: Boolean,
    play: () -> Unit,
    pause: () -> Unit,
    replay10: () -> Unit,
    forward10: () -> Unit,
    next: () -> Unit,
    previous: () -> Unit,
    playerButtonSize: Dp = 72.dp,
    sideButtonSize: Dp = 48.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(
            onClick = previous,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_skip_previous),
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.previous)
            )
        }
        IconButton(
            onClick = replay10,
        ) {
            Icon(
                painterResource(id =  R.drawable.ic_repeat),
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.replay10)
            )
        }
        Crossfade(targetState = playWhenReady, animationSpec = spring()) { targetPlayWhenReady ->
            if (targetPlayWhenReady) {
                IconButton(
                    onClick = pause,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pause),
                        modifier = Modifier.size(playerButtonSize),
                        contentDescription = stringResource(id = R.string.play)
                    )
                }
            } else {
                IconButton(
                    onClick = play,
                ) {
                    Icon(
                        painter = painterResource(id = AppIcons.Play.resourceId),
                        modifier = Modifier.size(playerButtonSize),
                        contentDescription = stringResource(id = R.string.play)
                    )
                }
            }
        }
        IconButton(
            onClick = forward10
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.forward10)
            )
        }
        IconButton(
            onClick = next
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_skip_next),
                modifier = Modifier.size(sideButtonSize),
                contentDescription = stringResource(id = R.string.next)
            )
        }
    }
}