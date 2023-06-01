package com.example.musicchallenge.domain.models

import com.example.musicchallenge.data.remotedatasource.dto.ContributorDto

data class Album(
    val id: Long?,
    val title: String?="",
    val cover: String?="",
    val cover_small: String?="",
    val cover_medium: String?="",
    val cover_big: String?="",
    val cover_xl: String?="",
    val md5_image: String?="",
    val tracklist: String?="",
    val type: String?=""
)