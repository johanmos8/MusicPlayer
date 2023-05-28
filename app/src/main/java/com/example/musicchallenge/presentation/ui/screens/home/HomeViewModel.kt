package com.example.musicchallenge.presentation.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.usesCases.MusicUseCase
import com.example.musicchallenge.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    private var musicUseCase: MusicUseCase
) : ViewModel() {


    init {
        getSongsBySearch("shakira")
        getGenres()

    }

    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _songs = MutableStateFlow<List<Song>>(emptyList())//listOf<Song>())
    val songs: StateFlow<List<Song>> = _songs

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres

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
                    musicUseCase.getSongsBySearch(query).collect { songs ->
                        _songs.value = songs
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

    fun getGenres() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = musicUseCase.getGenre()) {
                is Resource.Success -> {
                    _genres.value = response.data!!

                }
                is Resource.Error -> _state.value = _state.value.copy(
                    progressBarVisible = false,
                    errorMessage = "The request failed. Please try again."
                )
            }
        }


    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.PlaySound -> {
                    Log.d("Eventos", "playsound")
            }
/*            is HomeEvents.OnSelectGenre -> {
                _state.value = state.value.copy(
                    //  selectedGenre = event.genre,
                )
            }

            is HomeEvents.OnSelectAlbum -> {
                refreshAlbumTracks(event.album.id!!)
            }
            is HomeEvents.OnSelectArtist -> {
                _state.value = state.value.copy(
                    selectedArtist = event.artist
                )
            }*/
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
