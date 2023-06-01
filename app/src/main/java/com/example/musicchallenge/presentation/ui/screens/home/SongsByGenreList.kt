package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.R

@Composable
fun SongsByGenreList(songs: List<Song>) {

    LazyColumn {
        itemsIndexed(songs) { index: Int, song: Song ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF352547)),

                ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .background(color = Color(0xFF2E2D37))//TODO
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = song.artist?.picture,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(width = 80.dp, height = 60.dp)
                            .clip(RoundedCornerShape(10.dp))

                    )
                    Column {
                        Text(
                            text = song.title.toString(),
                            fontSize = 15.sp,
                            color = Color.White

                        )
                        Text(text = song.artist?.name.toString(),
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite_border_24),
                        contentDescription = "Fav",
                        tint = Color.White,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {

                            }

                    )
                }
            }
        }
    }
}