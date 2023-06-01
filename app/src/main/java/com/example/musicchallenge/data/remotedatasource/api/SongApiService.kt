package com.example.musicchallenge.data.remotedatasource.api

import com.example.musicchallenge.data.remotedatasource.dto.SearchResponse
import com.example.musicchallenge.data.remotedatasource.dto.chart.ChartDto
import com.example.musicchallenge.data.remotedatasource.dto.genre.GenreDataDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApiService {
    @GET("genre")
    suspend fun getGenres(): Response<GenreDataDto>

    @GET("/search")
    suspend fun getSongsByQuery(@Query("q") query: String): SearchResponse

    @GET("/chart")
    suspend fun getCharts(): Response<ChartDto>
}