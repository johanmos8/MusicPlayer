package com.example.musicchallenge.data.api.repositories

import android.util.Log
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.repositories.ISongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val remoteDataSource: MusicRemoteDataSource,
    private val songListMapper: SongResponseListToSongsMapper
) : ISongRepository {
    override suspend fun getSongById(): Song {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularSongs(): List<Song> {
        TODO("Not yet implemented")
    }

    override suspend fun getSongsByQuery(query: String): Flow<List<Song>> {
        return flow<List<Song>> {
            emit(songListMapper(remoteDataSource.getSongsByQuery(query).data))
        }.flowOn(Dispatchers.IO)

    }

}