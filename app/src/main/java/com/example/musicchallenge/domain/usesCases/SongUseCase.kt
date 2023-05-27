package com.example.musicchallenge.domain.usesCases

import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.repositories.ISongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SongUseCase @Inject constructor(
    private val iSongRepository: ISongRepository
) {

    suspend fun getSongsBySearch(query: String): Flow<List<Song>> =
        iSongRepository.getSongsByQuery(query)

}