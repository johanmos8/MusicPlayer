package com.example.musicchallenge.exoplayer

import androidx.media3.session.MediaBrowser
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_INDEX
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_POSITION_MS
import kotlinx.coroutines.launch
import javax.inject.Inject

class MusicServiceConnection @Inject constructor() {

    private var mediaBrowser: MediaBrowser? = null

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
/*
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
        coroutineScope.launch {
            setPlayingQueueUseCase(songs.map(Song::asSongModel))
        }}
        */
}
