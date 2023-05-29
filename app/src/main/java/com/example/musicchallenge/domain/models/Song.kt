package com.example.musicchallenge.domain.models

data class Song(
    val id: Long,
    val readable: Boolean,
    val title: String,
    val title_short: String?,
    val title_version: String?,
    val link: String?,
    val duration: Int,
    val rank: Int,
    val explicit_lyrics: Boolean,
    val explicit_content_lyrics: Int?,
    val explicit_content_cover: Int?,
    val preview: String,
    val md5_image: String,
    val artist: Artist?,
    val album: Album?,
    val type: String
)

data class Artist(
    val id: Long,
    val name: String,
    val link: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    val tracklist: String,
    val type: String
)

data class Album(
    val id: Long,
    val title: String,
    val cover: String,
    val cover_small: String,
    val cover_medium: String,
    val cover_big: String,
    val cover_xl: String,
    val md5_image: String,
    val tracklist: String,
    val type: String
)
