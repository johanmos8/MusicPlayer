package com.example.musicchallenge.domain.usesCases

import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import javax.inject.Inject

class   PlayPauseListUseCase @Inject constructor(private val musicServiceConnection: MusicServiceConnection) {
    operator fun invoke(
        list: List<Song>,
        startIndex: Int = Constants.DEFAULT_INDEX,
        isRunning: Boolean = false,
        playWhenReady: Boolean = false
    ) {
        if (isRunning) {
            if (!playWhenReady) {
                musicServiceConnection.play()
            } else {
                musicServiceConnection.pause()
            }
        } else {
           musicServiceConnection.playSongs(
                songs = list.map { it },
                startIndex = startIndex
            )
        }
    }
}
