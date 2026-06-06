package com.example.sokasinging.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R
import com.example.sokasinging.data.model.Song
import com.example.sokasinging.presentation.screens.SongItem
import com.example.sokasinging.ui.theme.AppTheme
import com.example.sokasinging.ui.theme.lavenderMuted
import com.example.sokasinging.ui.theme.selectedBorder
import kotlin.math.absoluteValue

@Composable
fun SongRow(song: Song, index: Int, isSelected: Boolean) {
    val rowModifier = if (isSelected) {
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                1.dp, MaterialTheme.colorScheme.selectedBorder,
                RoundedCornerShape(24.dp)
            )
            .padding(13.dp)
    } else {
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .padding(13.dp)
    }

    val palettes = listOf(
        listOf(Color(0xFF5FB6C0), Color(0xFF3C4A63), Color(0xFF8C4E7A)), // Row 1: Teal to Plum
        listOf(Color(0xFF3E385E), Color(0xFF7A4D7B), Color(0xFF2B2B2B)), // Row 2: Deep Purple to Dark
        listOf(Color(0xFF4CB5B9), Color(0xFF26324D), Color(0xFF4E5A7A)), // Row 3: Cyan to Navy
        listOf(Color(0xFF415F6B), Color(0xFF2B2B2B), Color(0xFF5A7A8C)), // Row 4: Slate to Blue-Grey
        listOf(Color(0xFF6D4E64), Color(0xFF445B78), Color(0xFF4E6D64)), // Muted Forest Mix
        listOf(Color(0xFF51558E), Color(0xFF29354E), Color(0xFF7D517E))  // Deep Sea Mix
    )
    val artworkColor = palettes[index % palettes.size]
    val artworkBrush = Brush.linearGradient(
        colors = artworkColor,
        start = Offset(0f, 0f),
        end = Offset.Infinite
    )

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(artworkBrush)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play_arrow),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = song.songTitle,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp,
            )
            Text(
                text = "${song.year} ",
//                • ${song.album}
                color = if (isSelected) MaterialTheme.colorScheme.lavenderMuted else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            painter = painterResource(
                id = R.drawable.ic_heart_check
            ),
            contentDescription = "Liked",
//                if (song.isLiked) "Liked" else "Like",
            tint = MaterialTheme.colorScheme.tertiary,
//                if (song.isLiked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(17.dp)
        )
    }
}

@Preview
@Composable
fun PreviewSongRow(){
    AppTheme() {
        SongRow(
            song = Song(songTitle = "Hope of the World", year = 2020),
            isSelected = false,
            index = 1
        )
    }
}