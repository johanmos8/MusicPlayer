package com.example.musicchallenge.exoplayer

import androidx.media3.session.MediaBrowser
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_INDEX
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_POSITION_MS
import com.example.musicchallenge.exoplayer.mapper.asMediaItem
import com.example.musicchallenge.exoplayer.state.MusicState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MusicServiceConnection @Inject constructor(
    //private val setPlayingQueueUseCase: SetPlayingQueueUseCase,

    ) {

    private var mediaBrowser: MediaBrowser? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    var _musicState = MutableStateFlow(MusicState())
    val musicState = _musicState.asStateFlow()

    val currentPosition = flow {
        while (currentCoroutineContext().isActive) {
            val currentPosition = mediaBrowser?.currentPosition ?: DEFAULT_POSITION_MS
            emit(currentPosition)
            delay(1L)
        }
    }

    fun skipPrevious() = mediaBrowser?.run {
        seekToPrevious()
        play()
    }

    fun play() = mediaBrowser?.play()
    fun pause() = mediaBrowser?.pause()

    fun skipNext() = mediaBrowser?.run {
        seekToNext()
        play()
    }

    fun skipTo(position: Long) = mediaBrowser?.run {
        seekTo(position)
        play()
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
}
