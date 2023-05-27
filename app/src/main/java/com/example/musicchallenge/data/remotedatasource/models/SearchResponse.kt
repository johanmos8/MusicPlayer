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
        val id: Long,
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
        val md5_image: String,
        val artist: Artist?,
        val album: Album?,
        val type: String
    )
}

data class Artist(
    val id: Long,
    val name: String,
    val link: String,
    val picture: String,
    @Json(name = "picture_small") val pictureSmall: String,
    @Json(name = "picture_medium") val pictureMedium: String,
    @Json(name = "picture_big") val pictureBig: String,
    @Json(name = "picture_xl") val pictureXl: String,
    val tracklist: String,
    val type: String
)

data class Album(
    val id: Long,
    val title: String,
    val cover: String,
    @Json(name = "cover_small") val coverSsmall: String,
    @Json(name = "cover_medium") val coverMedium: String,
    @Json(name = "cover_big") val coverBig: String,
    @Json(name = "cover_xl") val coverXl: String,
    @Json(name = "md5_image") val md5Image: String,
    val tracklist: String,
    val type: String
)
