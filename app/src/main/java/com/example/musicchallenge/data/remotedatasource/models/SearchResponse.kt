package com.example.musicchallenge.data.remotedatasource.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val data: List<Song>,
    val total: Int,
    val next: String?,

    ) {
    data class Song(
        val id: Int,
        val readable: Boolean,
        val title: String,
        @Json(name = "title_short") val titleShort: String,
        @Json(name = "title_version") val titleVersion: String,
        val link: String?,
        val duration: Int,
        val rank: Int,
        @Json(name = "explicit_lyrics") val explicitLyrics: Boolean,
        @Json(name = "explicit_content_lyrics") val explicitContentLyrics: Int,
        @Json(name = "explicit_content_cover") val explicitContentCover: Int,
        val preview: String?,
        val md5_image: Int,
        val artist: String?,
        val album: String?,
        val type: String
    )
}
/*
*     data class Song(
        val id: Int,
        val name: String?,
        val link: String?,
        val picture: String?,
        val picture_small: String?,
        val picture_medium: String?,
        val picture_big: String?,
        val picture_xl: String?,
        val nb_album: Int,
        val nb_fan: Int,
        val radio: Boolean,
        val trackList: String,
        val type: String
    )*/