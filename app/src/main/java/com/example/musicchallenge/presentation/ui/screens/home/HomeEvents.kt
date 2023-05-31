package com.example.musicchallenge.presentation.ui.screens.home

import com.example.musicchallenge.domain.models.Song

sealed class HomeEvents {
    //data class OnSelectGenre(val genre: Genre) : HomeEvents()
    //data class OnSelectAlbum(val album: Album) : HomeEvents()
    //data class OnSelectArtist(val artist: Artist) : HomeEvents()
    data class PlaySound(val isRunning: Boolean, val playWhenReady: Boolean, val idx: Int, val song: Song) :
        HomeEvents()

}
