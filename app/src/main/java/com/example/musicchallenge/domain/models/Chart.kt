package com.example.musicchallenge.domain.models

import com.example.musicchallenge.data.remotedatasource.dto.album.AlbumsDataDto

data class Chart(
    val tracks: TracksData?,
    val albums: AlbumsData?,
    val artists: ArtistsData?,
    val playlists: PlaylistsData?,
    val podcasts: PodcastsData?
)

data class TracksData(
    val data: List<Track>,
    val total: Int?
)

data class AlbumsData(
    val data: List<Album>,
    val total: Int?
)

data class ArtistsData(
    val data: List<Artist>,
    val total: Int?
)

data class PlaylistsData(
    val data: List<Playlist>,
    val total: Int?
)


data class User(
    val id: Long,
    val name: String,
    val tracklist: String,
    val type: String
)

data class PodcastsData(
    val data: List<Podcast>,
    val total: Int?
)


