package com.example.musicchallenge.data.remotedatasource

import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.remotedatasource.dto.SearchResponse
import com.example.musicchallenge.data.remotedatasource.dto.chart.ChartDto
import com.example.musicchallenge.data.remotedatasource.dto.genre.GenreDataDto
import retrofit2.Response


class MusicRemoteDataSourceImpl(
    private val songApiService: SongApiService,
) : MusicRemoteDataSource {

    override suspend fun getSongsByQuery(query: String): SearchResponse =
        songApiService.getSongsByQuery(query)

    override suspend fun getGenres(): Response<GenreDataDto> = songApiService.getGenres()
    override suspend fun getChart(): Response<ChartDto> = songApiService.getCharts()


}