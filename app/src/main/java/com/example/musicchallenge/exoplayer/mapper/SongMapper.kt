package com.example.musicchallenge.exoplayer.mapper

import android.net.Uri
import android.provider.MediaStore.Audio.Media
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.exoplayer.buildPlayableMediaItem


internal fun Song.asMediaItem()=
    buildPlayableMediaItem(
        mediaId = this.id.toString(),
        artistId = this.artist?.id,
        albumId = this.album?.id,
        mediaUri = Uri.parse(this.preview),
        artworkUri = Uri.parse(this.album?.cover),
        title = this.title,
        artist = artist?.name
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

internal fun MediaItem?.asSong() = Song(
    id = this?.mediaId?.toInt() ?: DEFAULT_MEDIA_ID,
    artistId = this?.mediaMetadata?.extras?.getLong(ARTIST_ID) ?: DEFAULT_ARTIST_ID,
    albumId = this?.mediaMetadata?.extras?.getLong(ALBUM_ID) ?: DEFAULT_ALBUM_ID,
    mediaUri = this?.requestMetadata?.mediaUri.orEmpty(),
    artworkUri = this?.mediaMetadata?.artworkUri.orEmpty(),
    title = this?.mediaMetadata?.title.orEmpty(),
    artist = this?.mediaMetadata?.artist.orEmpty(),
    album = this?.mediaMetadata?.albumTitle.orEmpty()
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
