package com.example.musicchallenge.domain.repositories

import com.example.musicchallenge.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface IMusicRepository {
    fun getPlayingQueue(): Flow<List<Song>>
    suspend fun setPlayingQueue(songs: List<Song>)
}
