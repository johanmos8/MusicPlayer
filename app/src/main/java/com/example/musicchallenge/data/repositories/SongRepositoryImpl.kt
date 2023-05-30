package com.example.musicchallenge.data.repositories


import com.example.musicchallenge.data.mappers.ChartResponseToChartMapper
import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.data.mappers.GenreDTOListToGenreMapper
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.domain.models.*
import com.example.musicchallenge.domain.repositories.ISongRepository
import com.example.musicchallenge.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val remoteDataSource: MusicRemoteDataSource,
    private val songListMapper: SongResponseListToSongsMapper,
    private val genreDTOToGenreMapper: GenreDTOListToGenreMapper,
    private val chartResponseToChartMapper: ChartResponseToChartMapper
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

    override suspend fun getGenres(): Resource<List<Genre>> {
        return try {
            val response = remoteDataSource.getGenres()
            val body = response.body()
            if (body != null && response.isSuccessful) {
                Resource.Success(genreDTOToGenreMapper(body.data))
            } else {
                Resource.Error("something went wrong")
            }
        } catch (error: Exception) {
            Resource.Error(error.message.toString())
        }

    }

    override suspend fun getChart(): Resource<Chart> {

        return try {
            val response = remoteDataSource.getChart()
            val body = response.body()
            if (body != null && response.isSuccessful) {
                // Mapeo de la respuesta a los modelos de datos de la capa de dominio
                Resource.Success(chartResponseToChartMapper(body))
            } else {
                Resource.Error("something went wrong")
            }
        } catch (error: Exception) {
            Resource.Error(error.message.toString())
        }

    }
}