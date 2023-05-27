package com.example.musicchallenge.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.usesCases.SongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class SharedViewModel @Inject constructor(
    private var songUseCase: SongUseCase
) : ViewModel() {


    init {
        getSongsBySearch("shakira")

    }
    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())//listOf<Song>())
    val songs: StateFlow<List<Song>> = _songs

    private val _state = MutableStateFlow<MusicPlayerViewState>(MusicPlayerViewState())
    val state: StateFlow<MusicPlayerViewState>
        get() = _state

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getSongsBySearch(query: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    songUseCase.getSongsBySearch(query).collect { songs ->
                        _songs.value=songs
                    }
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    progressBarVisible = false,
                    errorMessage = "The request failed. Please try again."
                )
                Log.d("ErrorFlow", "Error$e")
            }
        }
    }

}

data class MusicPlayerViewState(
    val progressBarVisible: Boolean = false,
    val errorMessage: String? = null,
    val songsList: List<Song> = emptyList()
)
/*
sealed class PageSequenceIntent {
    object FetchMostPopularPathSequences : PageSequenceIntent()
    // Add more user intents here, such as user actions in the UI
}
*/
