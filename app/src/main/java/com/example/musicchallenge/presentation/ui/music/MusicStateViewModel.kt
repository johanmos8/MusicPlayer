package com.example.musicchallenge.presentation.ui.music

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.exoplayer.state.MusicState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MusicStateViewModel @Inject constructor(
) : ViewModel() {
    private val _musicState = MutableStateFlow(MusicState())
    val musicState: StateFlow<MusicState> = _musicState.asStateFlow()

    fun updateMusicState(song: Song) {
        _musicState.update { currentState ->
            currentState.copy(currentSong = song)
        }

    }
}
