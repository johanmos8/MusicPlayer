package com.example.musicchallenge.data.localdatasource

//import FavoriteSong
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import com.example.movies.data.utils.StringUtils.DATA_STORE_FILE_NAME
import com.example.musicchallenge.FavoriteSong
import com.example.musicchallenge.FavoriteSongsData
import com.example.musicchallenge.data.di.ISongRepositoryModule
import com.example.musicchallenge.data.localdatasource.proto.FavoriteSongSerializer
import com.example.musicchallenge.data.localdatasource.proto.FavoriteSongsDataSerializer
import com.example.musicchallenge.data.mappers.toFavoriteSong
//import com.example.musicchallenge.data.localdatasource.proto.FavoriteSongSerializer
import com.example.musicchallenge.domain.models.Album
import com.example.musicchallenge.domain.models.Artist
import com.example.musicchallenge.domain.models.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import javax.inject.Inject


class MusicLocalDataSourceImpl @Inject constructor(
    private val context: Context
) : MusicLocalDataSource {

    private val Context.songProtoDataStore: DataStore<FavoriteSongsData> by dataStore(
        fileName = DATA_STORE_FILE_NAME,
        serializer = FavoriteSongsDataSerializer
    )

    override suspend fun getFavoriteSongs(): Flow<FavoriteSongsData> {
        return context.songProtoDataStore.data
    }

    override suspend fun addFavoriteSong(song: Song) {
        val newSong = FavoriteSong.newBuilder().setId(song.id)
            .setTitle(song.title)
            //.setTitleVersion(song.title_version)
            //.setTitleShort(song.title_short)
            .setPreview(song.preview)
            .build()

        context.songProtoDataStore.updateData { songLists ->
            if (songLists.toBuilder().songsList.contains(newSong)) {
                songLists
            } else {
                songLists.toBuilder().addSongs(newSong).build()
            }
        }


    }

    override suspend fun removeFavoriteSong(song: Song) {
        context.songProtoDataStore.updateData { songData ->
            val updatedSongsList = songData.toBuilder()
                .removeSongs(songData.songsList.indexOf(song.toFavoriteSong())) // Eliminar la canción de la lista
                .build()

            updatedSongsList
        }
    }


}