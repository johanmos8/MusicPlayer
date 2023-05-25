package com.example.musicchallenge.data.remotedatasource

import com.example.musicchallenge.data.remotedatasource.models.SearchResponse

interface MusicRemoteDataSource {
    suspend fun getSongsByQuery(query: String): SearchResponse
}