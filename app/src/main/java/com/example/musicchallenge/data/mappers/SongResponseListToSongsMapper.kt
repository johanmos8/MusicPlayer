package com.example.musicchallenge.data.mappers

import com.example.musicchallenge.data.remotedatasource.dto.SearchResponse
import com.example.musicchallenge.domain.models.Artist
import com.example.musicchallenge.domain.models.Song

class SongResponseListToSongsMapper {
    operator fun invoke(listSongs: List<SearchResponse.Song>): List<Song> {
        with(listSongs) {
            return listSongs.map { songResponse ->
                Song(
                    id = songResponse.id,
                    readable = songResponse.readable,
                    title = songResponse.title,
                    title_short = songResponse.titleShort,
                    title_version = songResponse.titleVersion,
                    link = songResponse.link,
                    duration = songResponse.duration,
                    rank = songResponse.rank,
                    explicit_lyrics = songResponse.explicitLyrics,
                    explicit_content_lyrics = songResponse.explicitContentLyrics,
                    explicit_content_cover = songResponse.explicitContentLyrics,
                    preview = songResponse.preview,
                    md5_image = songResponse.md5_image,
                    songResponse.artist?.let {
                        Artist(
                            id = it.id,
                            name = it.name,
                            link = it.link,
                            picture = it.picture,
                            picture_small = it.pictureSmall,
                            picture_medium = it.pictureMedium,
                            picture_big = it.pictureBig,
                            picture_xl = it.pictureXl,
                            tracklist = it.tracklist,
                            type = it.type
                        )
                    },

                    type = songResponse.type,
                    album = null
                )

            }
        }

    }
}