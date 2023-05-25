package com.example.musicchallenge.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.musicchallenge.presentation.SharedViewModel
import com.example.musicchallenge.presentation.ui.theme.MusicChallengeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Log.d("MainActivity", "main activity launched")
        sharedViewModel.songs.observe(this, Observer { songs ->
            // Process the observed songs list
            // Print the list to the console
            songs.forEach { song ->
                println(song.toString())
            }
        })
        sharedViewModel.getSongsBySearch("shakira")*/
        setContent {
            MusicChallengeTheme {


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(sharedViewModel = sharedViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(sharedViewModel: SharedViewModel) {

    val searchText by sharedViewModel.searchText.collectAsState()
    val songs by sharedViewModel.songs.collectAsState()
    val isSearching by sharedViewModel.isSearching.collectAsState()
    var active by remember { mutableStateOf(false) }

    Scaffold {
        SearchBar(
            modifier = Modifier.fillMaxSize(),
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
}
