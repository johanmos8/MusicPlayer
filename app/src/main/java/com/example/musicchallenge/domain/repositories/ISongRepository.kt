package com.example.musicchallenge.domain.repositories

import com.example.musicchallenge.domain.models.Chart
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ISongRepository {

    suspend fun getSongById(): Song
    suspend fun getSongsByQuery(query: String): Flow<List<Song>>
    suspend fun getGenres(): Resource<List<Genre>>
    suspend fun getChart(): Resource<Chart>
    suspend fun getFavoriteSongs(): Flow<List<Song>>
    suspend fun saveFavoriteSong(song: Song)
    suspend fun removeFavoriteSong(song: Song)

}