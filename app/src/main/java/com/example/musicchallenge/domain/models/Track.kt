package com.example.musicchallenge.domain.models

data class Track(
    val id: Long,
    val title: String?,
    val title_short: String?,
    val title_version: String?,
    val link: String?,
    val duration: Int?,
    val rank: Int?,
    val explicit_lyrics: Boolean?,
    val explicit_content_lyrics: Int?,
    val explicit_content_cover: Int?,
    val preview: String?,
    val md5_image: String?,
    val position: Int?,
    val artist: Artist?,
    val album: Album?,
    val type: String?
)