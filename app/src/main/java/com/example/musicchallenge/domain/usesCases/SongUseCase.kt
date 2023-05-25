package com.example.musicchallenge.domain.usesCases

import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.repositories.ISongRepository
import javax.inject.Inject

class SongUseCase @Inject constructor(
    private val iSongRepository: ISongRepository
) {

    suspend fun getSongsBySearch(query: String): List<Song> {
        return iSongRepository.getSongsByquery(query)
    }
}