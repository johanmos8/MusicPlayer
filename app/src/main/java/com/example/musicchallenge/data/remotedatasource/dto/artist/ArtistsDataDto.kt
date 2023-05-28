package com.example.musicchallenge.data.remotedatasource.dto.artist

import com.example.musicchallenge.data.remotedatasource.dto.artist.ArtistDto
import com.squareup.moshi.Json

data class ArtistsDataDto(
    @field:Json(name = "data"  ) var data  : List<ArtistDto> = emptyList(),
    @field:Json(name = "total" ) var total : Int?            = null
)
