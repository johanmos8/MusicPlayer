package com.example.musicchallenge.domain.usesCases

import com.example.musicchallenge.domain.models.Chart
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.repositories.ISongRepository
import com.example.musicchallenge.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicUseCase @Inject constructor(
    private val iSongRepository: ISongRepository
) {

    suspend fun getSongsBySearch(query: String): Flow<List<Song>> =
        iSongRepository.getSongsByQuery(query)

    suspend fun getGenre(): Resource<List<Genre>> =
        iSongRepository.getGenres()

    suspend fun getChart(): Resource<Chart> =
        iSongRepository.getChart()
}