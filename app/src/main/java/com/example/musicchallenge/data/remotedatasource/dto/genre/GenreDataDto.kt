package com.example.musicchallenge.data.remotedatasource.dto.genre

import com.squareup.moshi.Json

data class GenreDataDto(

    val genres: List<GenreDto> = emptyList()
)
