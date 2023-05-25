package com.example.musicchallenge.data.remotedatasource.api

import com.example.musicchallenge.data.remotedatasource.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApiService {

    @GET("/search/artist/?index=0&limit=2&output=json")
    suspend fun getSongsByQuery(@Query("q") query: String): SearchResponse
}