package com.example.musicchallenge.exoplayer.mapper

import android.net.Uri
import android.provider.MediaStore.Audio.Media
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import com.example.musicchallenge.domain.models.Album
import com.example.musicchallenge.domain.models.Artist
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_ALBUM_ID
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_ARTIST_ID
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_MEDIA_ID
import com.example.musicchallenge.exoplayer.ALBUM_ID
import com.example.musicchallenge.exoplayer.ARTIST_ID
import com.example.musicchallenge.exoplayer.buildPlayableMediaItem


internal fun Song.asMediaItem() =
    buildPlayableMediaItem(
        mediaId = this.id.toString(),
        artistId = this.artist?.id,
        albumId = this.album?.id,
        mediaUri = Uri.parse(this.preview),
        //artworkUri = Uri.parse(this.album?.cover)?:Uri.EMPTY,
        title = this.title,
        artist = artist?.name
    )

internal fun MediaItem?.asSong() = Song(
    id = this?.mediaId?.toLong() ?: DEFAULT_MEDIA_ID,
    artist = null,
    album = null,
    preview = this?.requestMetadata?.mediaUri.toString(),
    title = this?.mediaMetadata?.title.toString(),
    title_short = null,
    title_version = null,
    link = null,
    duration = null,
    rank = null,
    explicit_lyrics = false,
    explicit_content_lyrics = null,
    explicit_content_cover = null,
    md5_image = null,
    type = null,
)

/*
internal fun Song.asSongModel() = SongModel(
    id = id,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri.toString(),
    artworkUri = artworkUri.toString(),
    title = title,
    artist = artist,
    album = album
)




fun SongModel.asSong() = Song(
    id = id,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri.toUri(),
    artworkUri = artworkUri.toUri(),
    title = title,
    artist = artist,
    album = album
)

private fun Uri?.orEmpty() = this ?: Uri.EMPTY
private fun CharSequence?.orEmpty() = (this ?: "").toString()
*/
