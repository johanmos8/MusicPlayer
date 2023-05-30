package com.example.musicchallenge.domain.models

data class Artist(
    val id: Long?,
    val name: String?,
    val link: String?,
    val picture: String?,
    val picture_small: String?,
    val picture_medium: String?,
    val picture_big: String?,
    val picture_xl: String?,
    val radio: Boolean?,
    val tracklist: String?,
    val type: String?
)
