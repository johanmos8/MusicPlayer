package com.example.musicchallenge.data.remotedatasource.dto.playlist

import com.example.musicchallenge.data.remotedatasource.dto.playlist.PlayListDto
import com.squareup.moshi.Json

data class PlayListsDataDto(
    @field:Json(name = "data") var data: List<PlayListDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
