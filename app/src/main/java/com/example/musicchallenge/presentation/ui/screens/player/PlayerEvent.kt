package com.example.musicchallenge.presentation.ui.screens.player

sealed class PlayerEvent {
    object Play : PlayerEvent()
    object Pause : PlayerEvent()
    object SkipNext : PlayerEvent()
    object SkipPrevious : PlayerEvent()
    object Repeat : PlayerEvent()
    object Shuffle : PlayerEvent()

}
