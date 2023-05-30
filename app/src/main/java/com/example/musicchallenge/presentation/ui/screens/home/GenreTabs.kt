package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.musicchallenge.domain.models.Genre
import com.example.musicchallenge.domain.models.Song
import com.example.musicchallenge.presentation.ui.theme.LocalSpacing


@Composable
fun GenresTabs(
    genres: List<Genre>,
    selectedGenre: Genre?,
    onGenreSelected: (Genre) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIndex = genres.indexOfFirst { it == selectedGenre }
    val spacing = LocalSpacing.current
    val emptyTabIndicator: @Composable (List<TabPosition>) -> Unit = {}
    ScrollableTabRow(
        containerColor= Color.Transparent,
        selectedTabIndex = selectedIndex,
        edgePadding = spacing.spaceMediumLarge,
        indicator = emptyTabIndicator,
        modifier = modifier
    ) {
        genres.forEachIndexed { index, genre ->
            Tab(
                selected = index == selectedIndex,
                onClick = {onGenreSelected(genre) }) {
                ChoiceChipContent(
                    text = genre.name ?: "Unknown",
                    selected = index == selectedIndex,
                    modifier = Modifier.padding(
                        horizontal = spacing.spaceExtraSmall,
                        vertical = spacing.spaceMedium
                    )
                )
            }
        }
    }
}