package com.example.musicchallenge.data.remotedatasource

import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import kotlinx.coroutines.flow.flow


class MusicRemoteDataSourceImpl(
    private val songApiService: SongApiService,
) : MusicRemoteDataSource {

    override suspend fun getSongsByQuery(query: String) =   songApiService.getSongsByQuery(query)

}