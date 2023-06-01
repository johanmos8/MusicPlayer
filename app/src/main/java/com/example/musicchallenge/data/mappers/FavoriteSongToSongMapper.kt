package com.example.musicchallenge.data.mappers

//import FavoriteSong
import com.example.musicchallenge.FavoriteSong
import com.example.musicchallenge.FavoriteSongsData
import com.example.musicchallenge.data.remotedatasource.dto.SearchResponse
import com.example.musicchallenge.domain.models.Artist
import com.example.musicchallenge.domain.models.Song
import javax.inject.Inject

class FavoriteSongToSongMapper @Inject constructor() {
    operator fun invoke(song: FavoriteSong): Song {
        with(song) {
            return Song(
                id = song.id,
                readable = song.readable,
                title = song.title,
                title_short = song.titleShort,
                title_version = song.titleVersion,
                link = song.link,
                duration = null,
                rank = null,
                explicit_lyrics = song.explicitLyrics,
                explicit_content_lyrics = null,
                explicit_content_cover = null,
                preview = song.preview,
                md5_image = null,
                artist = null,
                type = song.type,
                album = null
            )


        }

    }
}

fun FavoriteSong.toSong() = Song(
    id = this.id,
    readable = this.readable,
    title = this.title,
    title_short = this.titleShort,
    title_version = this.titleVersion,
    link = this.link,
    duration = null,
    rank = null,
    explicit_lyrics = this.explicitLyrics,
    explicit_content_lyrics = null,
    explicit_content_cover = null,
    preview = this.preview,
    md5_image = null,
    artist = null,
    type = this.type,
    album = null
)

fun FavoriteSongsData.toSongList(): List<Song> {
    return songsList.map { favoriteSong ->
            Song(
                id = favoriteSong.id,
                readable = favoriteSong.readable,
                title = favoriteSong.title,
                title_short = favoriteSong.titleShort,
                title_version = favoriteSong.titleVersion,
                link = favoriteSong.link,
                duration = favoriteSong.duration.toInt(),
                rank = favoriteSong.rank.toInt(),
                explicit_lyrics = favoriteSong.explicitLyrics,
                explicit_content_lyrics = null,
                explicit_content_cover = null,
                preview = favoriteSong.preview,
                md5_image = favoriteSong.md5Image,
                type = favoriteSong.type,
                album = null,
                artist = null

            )

    }
}