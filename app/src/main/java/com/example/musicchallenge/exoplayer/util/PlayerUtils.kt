package com.example.musicchallenge.exoplayer.util

import androidx.media3.common.C
import androidx.media3.common.Player
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_DURATION_MS
import com.example.musicchallenge.exoplayer.state.PlaybackState
import com.example.musicchallenge.exoplayer.util.Constants.INVALID_PLAYBACK_STATE_ERROR_MESSAGE

internal fun Int.asPlaybackState() = when (this) {
    Player.STATE_IDLE -> PlaybackState.IDLE
    Player.STATE_BUFFERING -> PlaybackState.BUFFERING
    Player.STATE_READY -> PlaybackState.READY
    Player.STATE_ENDED -> PlaybackState.ENDED
    else -> error(INVALID_PLAYBACK_STATE_ERROR_MESSAGE)
}

internal fun Long.orDefaultTimestamp() = takeIf { it != C.TIME_UNSET } ?: DEFAULT_DURATION_MS
