package com.example.musicchallenge.presentation.ui.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    homeViewModel: HomeViewModel
) {
    val songs by homeViewModel.favoritetsongs.collectAsState()

    Spacer(Modifier.height(12.dp))

    Text(
        color = Color(0xFFFFFFFF), //TODO
        modifier = Modifier.padding(all = 4.dp),
        text = "Recent Favorites",
        style = MaterialTheme.typography.headlineLarge
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            itemsIndexed(songs) { index: Int, song: Song ->

                Card(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxSize()
                    //elevation = 8.dp,
                ) {
                    AsyncImage(
                        model = "https://api.deezer.com/album/430985247/image",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 200.dp, height = 150.dp)
                            .clip(RoundedCornerShape(20.dp))

                    )
                    Text(
                        text = song.title.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }

            }
        })


}