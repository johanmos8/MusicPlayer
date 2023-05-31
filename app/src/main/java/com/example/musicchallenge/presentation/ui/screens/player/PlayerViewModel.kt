package com.example.musicchallenge.presentation.ui.screens.player

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicchallenge.domain.utils.Constants.DEFAULT_POSITION_MS
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicServiceConnection: MusicServiceConnection,

) : ViewModel() {

    private lateinit var context: Context
    val musicState = musicServiceConnection.musicState
    val currentPosition = musicServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = DEFAULT_POSITION_MS
    )

    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    init {
        viewModelScope.launch {
            musicServiceConnection.musicState.collect { musicState ->

                Log.d("PlayerVM: ","${musicServiceConnection.musicState.value}")
            }
        }
    }
    fun initialize(context: Context) {
        this.context = context
    }

    fun onEvent(event: PlayerEvent) {
        when (event) {
            is PlayerEvent.Play -> play()
            PlayerEvent.Pause -> pause()
            PlayerEvent.SkipNext -> skipNext()
            PlayerEvent.SkipPrevious -> skipPrevious()
            PlayerEvent.Repeat -> repeat()
            PlayerEvent.Shuffle -> shuffle()
        }
    }

    fun skipPrevious() = musicServiceConnection.skipPrevious()
    fun play() = musicServiceConnection.play()
    fun pause() = musicServiceConnection.pause()
    fun skipNext() = musicServiceConnection.skipNext()
    fun repeat() = musicServiceConnection.repeat()
    fun shuffle() = musicServiceConnection.shuffle()


    fun stopPlayback() {
        // Lógica para detener la reproducción
        player.playWhenReady = false
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}