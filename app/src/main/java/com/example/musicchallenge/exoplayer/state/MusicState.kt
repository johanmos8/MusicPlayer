package com.example.musicchallenge.exoplayer.state

import android.net.Uri
import com.example.musicchallenge.domain.models.Artist
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_ALBUM_ID
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_ARTIST_ID
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_DURATION_MS
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_MEDIA_ID

data class MusicState(
    val currentSong: Song = Song(
        id = DEFAULT_MEDIA_ID,
        preview = Uri.EMPTY.toString(),
        title = "",
        artist = null,
        album = null,
        readable = true,
        title_short = "",
        title_version = "",
        link = "",
        duration = 0,
        rank = 0,
        explicit_lyrics = true,
        explicit_content_lyrics = 0,
        explicit_content_cover = 0,
        md5_image = "",
        type = ""
    ),
    val playbackState: PlaybackState = PlaybackState.IDLE,
    val playWhenReady: Boolean = false,
    val duration: Long = DEFAULT_DURATION_MS
)