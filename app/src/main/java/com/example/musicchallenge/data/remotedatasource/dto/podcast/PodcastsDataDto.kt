package com.example.musicchallenge.data.remotedatasource.dto.podcast

import com.example.musicchallenge.data.remotedatasource.dto.podcast.PodcastDto
import com.squareup.moshi.Json

data class PodcastsDataDto(
    @field:Json(name = "data") var data: List<PodcastDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
