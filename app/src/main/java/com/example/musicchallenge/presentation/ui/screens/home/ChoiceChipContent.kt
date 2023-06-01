package com.example.musicchallenge.presentation.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.musicchallenge.presentation.ui.theme.LocalSpacing


@Composable
fun ChoiceChipContent(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Surface(
        color = when {
            selected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
            else ->Color(0xFF352547)
        },
        contentColor = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> MaterialTheme.colorScheme.onSurface
        },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                horizontal = spacing.spaceMedium,
                vertical = spacing.spaceSmall
            )
        )
    }
}
