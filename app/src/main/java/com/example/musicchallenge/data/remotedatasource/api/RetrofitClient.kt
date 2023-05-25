package com.example.musicchallenge.data.remotedatasource.api

import com.example.movies.data.utils.StringUtils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object RetrofitClient {
    val retrofitService: SongApiService by lazy {
        retrofit.create(SongApiService::class.java)
    }
}*/