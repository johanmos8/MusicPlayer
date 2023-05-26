package com.example.musicchallenge.presentation.ui

import SampleData
import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicchallenge.R
import com.example.musicchallenge.presentation.SharedViewModel
import com.example.musicchallenge.presentation.ui.theme.typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicPlayerApp(sharedViewModel: SharedViewModel) {
// A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        tonalElevation = 5.dp,
    ) {
        Surface(
            modifier = Modifier
                .padding(all = 16.dp)
                .clip(RoundedCornerShape(15.dp)),
            color = MaterialTheme.colorScheme.tertiary
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                SearchBarRow(sharedViewModel)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = "Trending right now",
                    style = MaterialTheme.typography.headlineLarge
                )
                SongList(SampleData.conversationSample)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBarRow(sharedViewModel: SharedViewModel) {

    val searchText by sharedViewModel.searchText.collectAsState()
    val songs by sharedViewModel.songs.collectAsState()
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
fun SongList(messages: List<Message>) {
    LazyRow {
        items(messages) { message ->
            MusicCard(song = message)
        }
    }
}

@Composable
fun CardWithShape(
    modifier: Modifier
) {
    val paddingModifier = modifier.padding(8.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = paddingModifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "The Dark side",
                    modifier = paddingModifier,
                    style = typography.bodySmall
                )
                Text(
                    text = "Muse - Simulation theory",
                    modifier = paddingModifier,
                    style = typography.bodySmall
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFFFFF), CircleShape)
                    .padding(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_play_foreground),
                    contentDescription = "Play",
                    tint = Color.Black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}

@Composable
fun MusicCard(song: Message) {
    //Add padding around our message.
    Box(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(250.dp)
                .padding(all = 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()

        )
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
        )
        CardWithShape(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
