package com.example.musicchallenge.data.remotedatasource.api

import com.example.musicchallenge.data.remotedatasource.models.SearchResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface SongApiService {

    @GET("/search")
    suspend fun getSongsByQuery(@Query("q") query: String): SearchResponse
    //suspend fun getSongsByQuery(@Query("q") query: String): ResponseBody
}