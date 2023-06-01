package com.example.musicchallenge.data.mappers

import com.example.musicchallenge.data.remotedatasource.dto.album.AlbumDto
import com.example.musicchallenge.data.remotedatasource.dto.album.AlbumsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.artist.ArtistDto
import com.example.musicchallenge.data.remotedatasource.dto.artist.ArtistsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.chart.ChartDto
import com.example.musicchallenge.data.remotedatasource.dto.playlist.PlayListDto
import com.example.musicchallenge.data.remotedatasource.dto.playlist.PlayListsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.podcast.PodcastDto
import com.example.musicchallenge.data.remotedatasource.dto.podcast.PodcastsDataDto
import com.example.musicchallenge.data.remotedatasource.dto.track.TrackDataDto
import com.example.musicchallenge.data.remotedatasource.dto.track.TrackDto
import com.example.musicchallenge.domain.models.*
import javax.inject.Inject

class ChartResponseToChartMapper @Inject constructor() {
    operator fun invoke(charts: ChartDto): Chart {
        with(charts) {
            return Chart(
                albums = albums?.let { mapAlbumsData(it) },
                tracks = tracks?.let { mapTracksData(it) },
                artists = artists?.let { mapArtistsData(it) },
                playlists = playlists?.let { mapPlaylistsData(it) },
                podcasts = podcasts?.let { mapPodcastsData(it) }
            )
        }
    }

    private fun mapTracksData(trackDataDto: TrackDataDto): TracksData {
        return TracksData(
            data = trackDataDto.data.map { mapTrack(it) },
            total = trackDataDto.total
        )
    }

    private fun mapTrack(trackDto: TrackDto): Song {
        return Song(
            id = trackDto.id,
            title = trackDto.title,
            title_short = trackDto.titleShort,
            title_version = trackDto.titleVersion,
            link = trackDto.link,
            duration = trackDto.duration,
            rank = trackDto.rank,
            explicit_lyrics = trackDto.explicitLyrics,
            explicit_content_lyrics = trackDto.explicitContentLyrics,
            explicit_content_cover = trackDto.explicitContentCover,
            preview = trackDto.preview,
            md5_image = trackDto.md5Image,
            position = trackDto.trackPosition,
            artist = trackDto?.artist?.let { mapArtist(it) },
            album = trackDto.album?.let { mapAlbum(it) },
            type = trackDto.type
        )
    }

    private fun mapAlbumsData(albumsDataDto: AlbumsDataDto): AlbumsData {
        return AlbumsData(
            data = albumsDataDto.albums.map { mapAlbum(it) },
            total = albumsDataDto.total
        )
    }

    private fun mapAlbum(albumDto: AlbumDto): Album {
        return Album(
            id = albumDto.id,
            title = albumDto.title,
            cover = albumDto.cover,
            cover_small = albumDto.coverSmall,
            cover_medium = albumDto.coverMedium,
            cover_big = albumDto.coverBig,
            cover_xl = albumDto.coverXl,
            md5_image = albumDto.md5Image,
            tracklist = albumDto.tracklist,
            type = albumDto.type
        )
    }

    private fun mapArtistsData(artistsDataDto: ArtistsDataDto): ArtistsData {
        return ArtistsData(
            data = artistsDataDto.data.map { mapArtist(it) },
            total = artistsDataDto.total
        )
    }

    private fun mapArtist(artistDto: ArtistDto): Artist {
        return Artist(
            id = artistDto.id,
            name = artistDto.name,
            link = artistDto.link,
            picture = artistDto.picture,
            picture_small = artistDto.pictureSmall,
            picture_medium = artistDto.pictureMedium,
            picture_big = artistDto.pictureBig,
            picture_xl = artistDto.pictureXl,
            radio = artistDto.radio,
            tracklist = artistDto.tracklist,
            type = artistDto.type
        )
    }

    private fun mapPlaylistsData(playlistsDataDto: PlayListsDataDto): PlaylistsData {
        return PlaylistsData(
            data = playlistsDataDto.data.map { mapPlaylist(it) },
            total = playlistsDataDto.total
        )
    }

    private fun mapPlaylist(playlistDto: PlayListDto): Playlist {
        return Playlist(
            id = playlistDto.id,
            title = playlistDto.title,
            public = playlistDto.public,
            nb_tracks = playlistDto.nbTracks,
            link = playlistDto.link,
            picture = playlistDto.picture,
            picture_small = playlistDto.pictureSmall,
            picture_medium = playlistDto.pictureMedium,
            picture_big = playlistDto.pictureBig,
            picture_xl = playlistDto.pictureXl,
            checksum = playlistDto.checksum,
            tracklist = playlistDto.tracklist,
            creation_date = playlistDto.creationDate,
            md5_image = playlistDto.md5Image,
            picture_type = playlistDto.pictureType,
            user = playlistDto.picture,
            type = playlistDto.type
        )
    }

    private fun mapPodcastsData(podcastsDataDto: PodcastsDataDto): PodcastsData {
        return PodcastsData(
            data = podcastsDataDto.data.map { mapPodcast(it) },
            total = podcastsDataDto.total
        )
    }

    private fun mapPodcast(podcastDto: PodcastDto): Podcast {
        return Podcast(
            id = podcastDto.id,
            title = podcastDto.title,
            description = podcastDto.description,
            available = podcastDto.available,
            fans = podcastDto.fans,
            link = podcastDto.link,
            share = podcastDto.share,
            picture = podcastDto.picture,
            picture_small = podcastDto.pictureSmall,
            picture_medium = podcastDto.pictureMedium,
            picture_big = podcastDto.pictureBig,
            picture_xl = podcastDto.pictureXl,
            type = podcastDto.type
        )
    }
}
