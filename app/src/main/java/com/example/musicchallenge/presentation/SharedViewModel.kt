package com.example.musicchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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


    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _songs = MutableStateFlow(allSongs)//listOf<Song>())
    val songs = searchText.combine(_songs) { text, songs ->
        if (text.isBlank()) {
            songs
        } else {
            delay(2000L)
            songs.filter{
                it.doesMatchSearchQuery(text)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _songs.value
    )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
/*
    fun getSongsBySearch(query: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (!query.isNullOrEmpty()) {
                    //_songs.postValue(songUseCase.getSongsBySearch(query))
                }
            }
        }
    }*/

}
data class Song(
    val firstName: String,
    val lastName: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$firstName$lastName",
            "$firstName $lastName",
            "${firstName.first()} ${lastName.first()}",
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

private val allSongs = listOf(
    Song(
        firstName = "Philipp",
        lastName = "Lackner"
    ),
    Song(
        firstName = "Beff",
        lastName = "Jezos"
    ),
    Song(
        firstName = "Chris P.",
        lastName = "Bacon"
    ),
    Song(
        firstName = "Jeve",
        lastName = "Stops"
    ),
)