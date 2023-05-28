package com.example.musicchallenge.data.remotedatasource

import com.example.musicchallenge.data.remotedatasource.dto.SearchResponse
import com.example.musicchallenge.data.remotedatasource.dto.album.AlbumDto
import com.example.musicchallenge.data.remotedatasource.dto.artist.ArtistDto
import com.example.musicchallenge.data.remotedatasource.dto.chart.ChartDto
import com.example.musicchallenge.data.remotedatasource.dto.genre.GenreDataDto
import com.example.musicchallenge.data.remotedatasource.dto.genre.GenreDto
import com.example.musicchallenge.data.remotedatasource.dto.radio.RadiosDataDto
import com.example.musicchallenge.data.remotedatasource.dto.track.TrackDataDto
import com.example.musicchallenge.domain.utils.Resource
import retrofit2.Response

interface MusicRemoteDataSource {
    suspend fun getSongsByQuery(query: String): SearchResponse
    suspend fun getGenres(): Response<GenreDataDto>
/*
    suspend fun getAlbum(id: Int): Resource<AlbumDto>

    suspend fun getArtist(id: Int): Resource<ArtistDto>

    suspend fun getArtistTrack(
        id: Int,
        limit: Int = 50,
        index: Int = 0
    ): Resource<TrackDataDto>

    suspend fun getArtistTracksPaging(
        query: String
    ): Resource<TrackDataDto>

    suspend fun getChart(id: Int): Resource<ChartDto>

    suspend fun getRadios(): Resource<RadiosDataDto>*/
}