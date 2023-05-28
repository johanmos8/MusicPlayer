package com.example.musicchallenge.domain.repositories

import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ISongRepository {

    suspend fun getSongById(): Song
    suspend fun getPopularSongs(): List<Song>
    suspend fun getSongsByQuery(query: String): Flow<List<Song>>
    suspend fun getGenres(): Resource<List<Genre>>
}