package com.example.musicchallenge.exoplayer

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.common.Player.REPEAT_MODE_ONE
import androidx.media3.session.MediaBrowser
import androidx.media3.session.SessionToken
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_INDEX
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_POSITION_MS
import com.example.musicchallenge.exoplayer.mapper.asMediaItem
import com.example.musicchallenge.exoplayer.mapper.asSong
import com.example.musicchallenge.exoplayer.state.MusicState
import com.example.musicchallenge.exoplayer.util.asPlaybackState
import com.example.musicchallenge.exoplayer.util.orDefaultTimestamp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.guava.await
import javax.inject.Inject

class MusicServiceConnection @Inject constructor(
    @ApplicationContext context: Context,
    //private val setPlayingQueueUseCase: SetPlayingQueueUseCase,

) {

    private var mediaBrowser: MediaBrowser? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    private val _musicState = MutableStateFlow(MusicState())
    val musicState = _musicState.asStateFlow()

    val currentPosition = flow {
        while (currentCoroutineContext().isActive) {
            val currentPosition = mediaBrowser?.currentPosition ?: DEFAULT_POSITION_MS
            emit(currentPosition)
            delay(1L)
        }
    }

    init {
        coroutineScope.launch {
            mediaBrowser = MediaBrowser.Builder(
                context,
                SessionToken(context, ComponentName(context, MusicPlayerService::class.java))
            ).buildAsync().await().apply { addListener(PlayerListener()) }
            //updatePlayingQueue()
        }
    }

    fun play() = mediaBrowser?.play()
    fun pause() = mediaBrowser?.pause()

    fun skipNext() = mediaBrowser?.run {
        seekToNext()
        play()
    }
    fun skipPrevious() = mediaBrowser?.run {
        seekToPrevious()
        play()
    }
    fun shuffle() = mediaBrowser?.run {
        shuffleModeEnabled = !shuffleModeEnabled
    }

    fun repeat() = mediaBrowser?.run {
        if (repeatMode == 0) {
            repeatMode = REPEAT_MODE_OFF
        } else {
            repeatMode = REPEAT_MODE_ONE
        }
    }

    fun playSongs(
        songs: List<Song>,
        startIndex: Int = DEFAULT_INDEX,
        startPositionMs: Long = DEFAULT_POSITION_MS
    ) {
        mediaBrowser?.run {
            setMediaItems(songs.map(Song::asMediaItem), startIndex, startPositionMs)
            prepare()
            play()
        }
        /*   coroutineScope.launch {
            setPlayingQueueUseCase(songs.map(Song::asSongModel))
        }}*/
    }

    private inner class PlayerListener : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            if (events.containsAny(
                    Player.EVENT_PLAYBACK_STATE_CHANGED,
                    Player.EVENT_MEDIA_METADATA_CHANGED,
                    Player.EVENT_PLAY_WHEN_READY_CHANGED
                )
            ) {
                updateMusicState(player)
            }

            /* if (events.contains(Player.EVENT_MEDIA_ITEM_TRANSITION)) {
                 updatePlayingQueueIndex(player)
             }*/
        }
    }

    private fun updateMusicState(player: Player) = with(player) {
        _musicState.update {
            it.copy(
                currentSong = currentMediaItem.asSong(),
                playbackState = playbackState.asPlaybackState(),
                playWhenReady = playWhenReady,
                duration = duration.orDefaultTimestamp()
            )
        }
    }
}
