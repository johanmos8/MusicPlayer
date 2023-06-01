package com.example.musicchallenge.domain.usesCases

import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import javax.inject.Inject

class PlayListUseCase @Inject constructor(private val musicServiceConnection: MusicServiceConnection) {
    operator fun invoke(list: List<Song>, startIndex: Int = Constants.DEFAULT_INDEX) =
        musicServiceConnection.playSongs(
            songs = list.map { it },
            startIndex = startIndex
        )
}
