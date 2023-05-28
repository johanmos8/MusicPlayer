package com.example.musicchallenge.data.remotedatasource.dto.radio

import com.example.musicchallenge.data.remotedatasource.dto.radio.RadioDto
import com.squareup.moshi.Json

data class RadiosDataDto(
    @field:Json(name = "data") var data: List<RadioDto> = emptyList()
)
