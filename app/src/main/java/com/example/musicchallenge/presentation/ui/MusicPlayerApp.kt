package com.example.musicchallenge.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.SharedViewModel


@Composable
fun MusicPlayerApp(sharedViewModel: SharedViewModel) {

    val viewState by sharedViewModel.state.collectAsState()
    val songs by sharedViewModel.songs.collectAsState() // Collect the flow as a state

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        tonalElevation = 5.dp,
    ) {
        Surface(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(15.dp)),
            color = Color(0xFF352547) //TODO MaterialTheme.colorScheme.tertiary
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                SearchBarRow(sharedViewModel)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    color= Color(0xFFFFFFFF), //TODO
                    modifier = Modifier.padding(all = 4.dp),
                    text = "Trending right now",
                    style = MaterialTheme.typography.headlineLarge
                )
                if (songs.isNotEmpty()) {
                    SongList(songs)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBarRow(sharedViewModel: SharedViewModel) {

    val searchText by sharedViewModel.searchText.collectAsState()
    val isSearching by sharedViewModel.isSearching.collectAsState()
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.inverseOnSurface),

        query = searchText,
        onQueryChange = sharedViewModel::onSearchTextChange,
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (searchText.isNotEmpty()) {

                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }

        }
    ) {

    }

}

@Composable
fun SongList(songs: List<Song>) {
    LazyRow {
        items(songs) { song ->
            MusicCard(song = song)
        }
    }
}



