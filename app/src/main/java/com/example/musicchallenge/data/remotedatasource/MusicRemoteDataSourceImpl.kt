package com.example.musicchallenge.data.source.remote

import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.remotedatasource.models.SearchResponse

class MusicRemoteDataSourceImpl(
    private val songApiService: SongApiService,
    private val songResponseListToSongsMapper: SongResponseListToSongsMapper
) : MusicRemoteDataSource {

    override suspend fun getSongsByQuery(query: String): SearchResponse {
        return songApiService.getSongsByQuery(query)

    }
}