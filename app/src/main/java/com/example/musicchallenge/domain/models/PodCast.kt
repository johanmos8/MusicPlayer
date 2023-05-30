package com.example.musicchallenge.domain.models

data class Podcast(
    val id: Long?,
    val title: String?,
    val description: String?,
    val available: Boolean?,
    val fans: Int?,
    val link: String?,
    val share: String?,
    val picture: String?,
    val picture_small: String?,
    val picture_medium: String?,
    val picture_big: String?,
    val picture_xl: String?,
    val type: String?
)