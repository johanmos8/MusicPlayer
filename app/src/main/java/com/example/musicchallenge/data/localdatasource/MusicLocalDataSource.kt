package com.example.musicchallenge.data.localdatasource

import com.example.musicchallenge.FavoriteSong
import com.example.musicchallenge.FavoriteSongsData
import com.example.musicchallenge.domain.models.Song
import kotlinx.coroutines.flow.Flow

interface MusicLocalDataSource {

    suspend fun getFavoriteSongs(): Flow<FavoriteSongsData>


    suspend fun addFavoriteSong(son: Song)


    suspend fun removeFavoriteSong(son: Song)
}