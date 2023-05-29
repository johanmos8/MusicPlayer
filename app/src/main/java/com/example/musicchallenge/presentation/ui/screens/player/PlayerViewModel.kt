package com.example.musicchallenge.presentation.ui.screens.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_POSITION_MS
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,

) : ViewModel() {

    private lateinit var context: Context
    val currentPosition = musicServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = DEFAULT_POSITION_MS,
    )
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    fun initialize(context: Context) {
        this.context = context
    }




    fun stopPlayback() {
        // Lógica para detener la reproducción
        player.playWhenReady = false
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}