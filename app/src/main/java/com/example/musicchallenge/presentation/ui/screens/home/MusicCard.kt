package com.example.musicchallenge.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.musicchallenge.R
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.domain.models.Track
import com.example.musicchallenge.presentation.ui.screens.home.HomeViewModel
import com.example.musicchallenge.presentation.ui.theme.typography

@Composable
fun MusicCard(
    song: Song,
    homeViewModel: HomeViewModel,
    onClick: (Boolean) -> Unit,
    backgroundColor: Color = Color.Transparent
) {
    val musicState by homeViewModel.state.collectAsState()
    //val currentPosition by homeViewModel.currentPosition.collectAsState()
    val isRunning = musicState.currentSong?.id == song.id
    var isFav by remember{ mutableStateOf(false) }
    //Add padding around our message.
    val icon = if (isFav) {
        R.drawable.ic_favorite_24
    } else {
        R.drawable.ic_favorite_border_24
    }
    Box(
        modifier = Modifier
            .padding(5.dp)

    ) {

        AsyncImage(
            model = song.artist?.picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 250.dp, height = 200.dp)
                .clip(RoundedCornerShape(20.dp))

        )
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Fav",
            tint = if (isFav) Color.Red else Color.White,
            modifier = Modifier
                .size(40.dp)
                .fillMaxSize()
                .align(Alignment.TopEnd)
                .padding(top = 5.dp, end = 5.dp)
                .clickable {

                    if(isFav){
                        homeViewModel.saveFavoriteSong(song)
                    }else{
                        homeViewModel.removeFavoriteSong(song)
                    }
                    isFav = !isFav
                }

        )
        CardWithShape(
            song,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(220.dp)
                .padding(bottom = 10.dp)
                .clickable {
                    onClick(false)
                }

        )

    }
}

@Composable
fun CardWithShape(
    song: Song,
    modifier: Modifier
) {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(color = Color(0xFF25247F))//TODO
                .padding(10.dp)
                .fillMaxWidth()
        ) {

            Column {
                Text(
                    text = song.title ?: "Unknown",
                    modifier = Modifier.requiredWidthIn(max = 140.dp),
                    style = typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color(0xFFFFFFFF), //TODO

                )
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_music_note),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier
                            .size(15.dp)
                            .fillMaxSize()
                    )
                    Text(
                        text = song.title_short ?: "",
                        modifier = Modifier.requiredWidthIn(max = 140.dp),
                        style = typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = Color(0xFFFFFFFF), //TODO
                    )
                }
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

