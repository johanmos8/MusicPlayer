package com.example.musicchallenge.data.remotedatasource.dto.chart

import com.example.musicchallenge.data.remotedatasource.dto.album.AlbumsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.artist.ArtistsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.playlist.PlayListsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.podcast.PodcastsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.track.TrackDataDto
import com.squareup.moshi.Json

data class ChartDto(
    @field:Json(name = "tracks") var tracks: TrackDataDto? = TrackDataDto(),
    @field:Json(name = "albums") var albums: AlbumsDataDto? = AlbumsDataDto(),
    @field:Json(name = "artists") var artists: ArtistsDataDto? = ArtistsDataDto(),
    @field:Json(name = "playlists") var playlists: PlayListsDataDto? = PlayListsDataDto(),
    @field:Json(name = "podcasts") var podcasts: PodcastsDataDto? = PodcastsDataDto()
)
