package com.example.musicchallenge.presentation.ui.screens.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicchallenge.domain.models.Chart
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.usesCases.MusicUseCase
import com.example.musicchallenge.domain.usesCases.PlayListUseCase
import com.example.musicchallenge.domain.usesCases.PlayPauseListUseCase
import com.example.musicchallenge.domain.usesCases.favorite.GetAllFavoriteSongsUseCase
import com.example.musicchallenge.domain.usesCases.favorite.SaveFavoriteSongUseCase
import com.example.musicchallenge.domain.utils.Resource
import com.example.musicchallenge.exoplayer.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@OptIn(FlowPreview::class)
class HomeViewModel @Inject constructor(
    private var musicUseCase: MusicUseCase,
    private val playPauseListUseCase: PlayPauseListUseCase,
    private val playListUseCase: PlayListUseCase,
    private val musicServiceConnection: MusicServiceConnection,
    private val getAllFavoriteSongsUseCase: GetAllFavoriteSongsUseCase,
    private val saveFavoriteSongUseCase: SaveFavoriteSongUseCase
) : ViewModel() {


    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _songs = MutableStateFlow<List<Song>>(emptyList())//listOf<Song>())
    val songs: StateFlow<List<Song>> = _songs

    private val _mainListsongs = MutableStateFlow<List<Song>>(emptyList())//listOf<Song>())
    val mainListSongs: StateFlow<List<Song>> = _mainListsongs

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres

    private val _chart = MutableStateFlow<Chart?>(null)
    val chart: MutableStateFlow<Chart?> = _chart

    private val _favoritetsongs = MutableStateFlow<List<Song>>(emptyList())//listOf<Song>())
    val favoritetsongs: StateFlow<List<Song>> = _favoritetsongs

    private val _selectedGenre = MutableStateFlow<Genre?>(null)

    private val _state = MutableStateFlow<HomeViewState>(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state
    private lateinit var context: Context

    val currentPosition: Long = 20 /*currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = Constants.DEFAULT_POSITION_MS,
    )*/
    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context)
            .setHandleAudioBecomingNoisy(true)
            .build()
    }

    init {
        getGenres()
        getChart()
        getAllObjects()
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.

            combine(
                _genres,
                _selectedGenre
            ) { genres, selectedGenre ->

                HomeViewState(
                    genres = genres,
                    selectedGenre = selectedGenre
                )
            }.collect { _state.value = it }
        }
    }

    fun saveFavoriteSong(song: Song) {
        viewModelScope.launch {
            saveFavoriteSongUseCase.invoke(song)
        }
    }

    fun getAllObjects() {
        viewModelScope.launch {
         getAllFavoriteSongsUseCase.invoke().collect{songs ->
             _favoritetsongs.value = songs
            }

        }

    }

    private fun getChart() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val response = musicUseCase.getChart()) {
                is Resource.Success -> {
                    _chart.value = response.data
                    _mainListsongs.value = response.data?.tracks?.data ?: emptyList()
                }
                is Resource.Error -> _state.value = _state.value.copy(
                    progressBarVisible = false,
                    errorMessage = "The request failed. Please try again."
                )
            }
        }
    }

    fun initialize(context: Context) {
        this.context = context
    }

    fun onSearchTextChange(query: String) {
        Log.d("Searching", "$query:String")
        _searchText.value = query
        if (query.isNotEmpty()) {
            getSongsBySearch(query)
        } else {
            getChart()
        }

        Log.d("Searching", "${_songs.value}")
        if (_songs.value.isNotEmpty()) {
            _mainListsongs.value = _songs.value
        } else {
            _chart.value?.let {

                _mainListsongs.value = it.tracks?.data ?: emptyList()
            }
        }

    }

    fun getSongsBySearch(query: String) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(progressBarVisible = true)
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
                    // If we haven't got a selected category yet, select the first
                    if (_selectedGenre.value != null) {
                        _selectedGenre.value = response.data?.get(0)
                    }
                }
                is Resource.Error -> _state.value = _state.value.copy(
                    progressBarVisible = false,
                    errorMessage = "The request failed. Please try again."
                )
            }
        }


    }

    fun play() {
        playListUseCase.invoke(songs.value)
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.PlaySound -> {
                Log.d("HomeViewModel", "${_mainListsongs.value}")

                playPauseListUseCase(
                    isRunning = event.isRunning,
                    playWhenReady = event.playWhenReady,
                    startIndex = event.idx,
                    list = _mainListsongs.value,

                    )
                onSongSelected(event.song)
                Log.d("HomeViewModel", "Click en PlaySound")
            }
            //is HomeEvents.PlaySound -> startPlayback()
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

    fun onSongSelected(song: Song) {

        _state.update { currentState ->
            _state.value.copy(
                currentSong = song
            )

        }
    }

    fun onGenreSelected(genre: Genre) {
        _selectedGenre.value = genre
    }

    fun startPlayback() {
        // Configurar el reproductor con la URL del track
        _state.value.currentSong.let { song ->
            val mediaItem = MediaItem.fromUri(song?.preview!!)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.playWhenReady = true
        }

    }


}

data class HomeViewState(
    val progressBarVisible: Boolean = false,
    val errorMessage: String? = null,
    val songsList: List<Song> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val selectedGenre: Genre? = null,
    val currentSong: Song? = null,

    )
