package com.example.musicchallenge.data.remotedatasource

import com.example.musicchallenge.data.remotedatasource.models.SearchResponse
import kotlinx.coroutines.flow.Flow

interface MusicRemoteDataSource {
    suspend fun getSongsByQuery(query: String): SearchResponse
}