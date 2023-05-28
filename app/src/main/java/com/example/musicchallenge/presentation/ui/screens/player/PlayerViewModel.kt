package com.example.musicchallenge.presentation.ui.screens.player

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicchallenge.exoplayer.MusicPlayerService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
) : ViewModel(){
    private lateinit var context: Context

    fun initialize(context: Context) {
        this.context = context
    }
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    fun startPlayback(trackUrl: String) {
        // Configurar el reproductor con la URL del track
        val mediaItem = MediaItem.fromUri(trackUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true
    }


    fun stopPlayback() {
        // Lógica para detener la reproducción
        player.playWhenReady = false
    }
}