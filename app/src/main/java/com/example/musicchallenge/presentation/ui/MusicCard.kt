package com.example.musicchallenge.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musicchallenge.R
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.theme.typography

@Composable
fun MusicCard(
    song: Song,

    onClick: (Boolean) -> Unit
) {

    //Add padding around our message.
    Box(
        modifier = Modifier.padding(8.dp)
            .clickable { onClick(true) }

    ) {
        AsyncImage(
            model = song.artist?.picture,
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .padding(all = 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()
        )
        /*Image(
            painter = rememberImagePainter=//painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(250.dp)
                .padding(all = 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxSize()

        )*/
        CardWithShape(
            song,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun CardWithShape(
    song: Song,
    modifier: Modifier
) {
    val paddingModifier = modifier.padding(8.dp)
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = paddingModifier


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(color = Color(0xFF25247F))//TODO
        ) {
            Column {
                Text(
                    text = song.title,
                    modifier = paddingModifier.requiredWidthIn(max = 250.dp),
                    style = typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color(0xFFFFFFFF), //TODO

                )
                Text(
                    text = song.title_short ?: "",
                    modifier = paddingModifier.requiredWidthIn(max = 250.dp),
                    style = typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = Color(0xFFFFFFFF), //TODO

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

