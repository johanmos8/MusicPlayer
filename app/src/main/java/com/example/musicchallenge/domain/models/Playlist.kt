package com.example.musicchallenge.domain.models

data class Playlist(
    val id: Long?,
    val title: String?,
    val public: Boolean?,
    val nb_tracks: Int?,
    val link: String?,
    val picture: String?,
    val picture_small: String?,
    val picture_medium: String?,
    val picture_big: String?,
    val picture_xl: String?,
    val checksum: String?,
    val tracklist: String?,
    val creation_date: String?,
    val md5_image: String?,
    val picture_type: String?,
    val user: String?,
    val type: String?
)