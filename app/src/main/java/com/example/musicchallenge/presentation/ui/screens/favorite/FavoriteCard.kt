package com.example.musicchallenge.presentation.ui.screens.favorite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.CardWithShape

@Composable
fun FavoriteCard(
    song: Song
) {
    Column(
        modifier = Modifier
            .padding(5.dp)


    ) {
        AsyncImage(
            model = song.artist?.picture,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 200.dp, height = 150.dp)
                .clip(RoundedCornerShape(20.dp))

        )
        Text(
            text = song.artist?.name ?: "Unknown"
        )
    }
}