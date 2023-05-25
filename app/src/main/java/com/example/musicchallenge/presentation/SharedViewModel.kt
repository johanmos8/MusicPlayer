package com.example.musicchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.usesCases.SongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private var songUseCase: SongUseCase
) : ViewModel() {


    //Definition of Livedata for movies by query.
    private val _songs = MutableLiveData<List<Song>>()
    val songs: LiveData<List<Song>> = _songs

    fun getSongsBySearch(query: String) {
        viewModelScope.launch {

            if (!query.isNullOrEmpty()) {
                _songs.value = songUseCase.getSongsBySearch(query)
            }


        }
    }
}