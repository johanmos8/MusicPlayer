package com.example.musicchallenge.domain.repositories

import com.example.musicchallenge.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface ISongRepository {

    suspend fun getSongById(): Song
    suspend fun getPopularSongs(): List<Song>
    suspend fun getSongsByQuery(query:String): Flow<List<Song>>
}