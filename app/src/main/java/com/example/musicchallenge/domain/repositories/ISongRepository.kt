package com.example.musicchallenge.domain.repositories

import com.example.musicchallenge.domain.models.Song

interface ISongRepository {

    suspend fun getSongById(): Song
    suspend fun getPopularSongs(): List<Song>
    suspend fun getSongsByquery(query:String): List<Song>
}