package com.example.musicchallenge.data.mappers

import com.example.musicchallenge.data.remotedatasource.models.SearchResponse
import com.example.musicchallenge.domain.models.Song
import javax.inject.Inject


class SongReponseToSongMapper @Inject constructor(){
    operator fun invoke(song: SearchResponse.Song): Song {
        with(song) {
            return Song(
                id = song.id,
                readable = song.readable,
                title = song.title,
                title_short = song.titleShort,
                title_version = song.titleVersion,
                link = song.link,
                duration = song.duration,
                rank = song.rank,
                explicit_lyrics = song.explicitLyrics,
                explicit_content_lyrics = song.explicitContentLyrics,
                explicit_content_cover = song.explicitContentCover,
                preview = song.preview,
                md5_image = song.md5_image,
                artist = null,
                album = null,
                type = song.type
            )
        }
    }
}