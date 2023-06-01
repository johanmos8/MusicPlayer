package com.example.musicchallenge.domain.usesCases.favorite

import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.repositories.ISongRepository
import com.example.musicchallenge.domain.utils.Constants
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import javax.inject.Inject

class RemoveFavoriteSongUseCase @Inject constructor(
    private val iSongRepository: ISongRepository

) {
    suspend operator fun invoke(song: Song) = iSongRepository.removeFavoriteSong(song)

}