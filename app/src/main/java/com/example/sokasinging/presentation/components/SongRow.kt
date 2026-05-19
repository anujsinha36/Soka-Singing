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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R
import com.example.sokasinging.presentation.screens.SongItem
import com.example.sokasinging.ui.theme.lavenderMuted
import com.example.sokasinging.ui.theme.selectedBorder

@Composable
fun SongRow(song: SongItem, isSelected: Boolean) {
    val rowModifier = if (isSelected) {
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.selectedBorder,
                RoundedCornerShape(24.dp))
            .padding(13.dp)
    } else {
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .padding(13.dp)
    }

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f))
                    )
                )
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
                text = song.title,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp,
            )
            Text(
                text = "${song.artist} • ${song.album}",
                color = if (isSelected) MaterialTheme.colorScheme.lavenderMuted else MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            painter = painterResource(
                id = R.drawable.ic_heart_plus
            ),
            contentDescription = if (song.isLiked) "Liked" else "Like",
            tint = if (song.isLiked) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(17.dp)
        )

    }
}