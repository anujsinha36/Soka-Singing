package com.example.sokasinging.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R
import com.example.sokasinging.presentation.components.AppTopBar
import com.example.sokasinging.presentation.components.SmallIconButton
import com.example.sokasinging.presentation.components.SongRow
import com.example.sokasinging.ui.theme.*

// ─── Data Models ─────────────────────────────────────────────────────────────
data class SongItem(
    val title: String,
    val artist: String,
    val album: String,
    val isLiked: Boolean = false,
    val isPlaying: Boolean = false,
)

data class RecentCard(
    val title: String,
    val genre: String,
    val gradientColors: List<Color>,
)

data class CollectionCard(
    val title: String,
    val subtitle: String,
)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Music Library screen for Soka Player.
 */
@Composable
fun MusicLibraryScreen(
    songs: List<SongItem> = previewSongs(),
    nowPlayingTitle: String = "Ethereal Drift",
    nowPlayingArtist: String = "Solaris Collective",
    onMenuClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onViewHistoryClick: () -> Unit = {},
    onMiniPlayerClick: () -> Unit = {},
    onMiniPlayerPlayPause: () -> Unit = {},
//    check how to enable pause and play based on when player is active and being played/paused for icon change
    onMiniPlayerPaused: Boolean
) {
    Box(modifier = Modifier.fillMaxSize().
    background(MaterialTheme.colorScheme.background)) {

        // ── Scrollable content ────────────────────────────────────────────
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//                // bottom padding so mini-player doesn't cover last row
//                .padding(bottom = 100.dp)
//        )
            // Top App Bar
//            AppTopBar(
//                onMenuClick = onMenuClick,
//                onNotificationClick = onNotificationClick,
//            )

            // Content Canvas
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp).padding(bottom = 100.dp),
//                verticalArrangement = Arrangement.spacedBy(56.dp),
            ) {
               item { Spacer(modifier = Modifier.height(40.dp)) }  // top padding inside canvas

//                 Recently Played
               item {RecentlyPlayedSection(onViewHistoryClick = onViewHistoryClick)  }
                item { Spacer(modifier = Modifier.height(56.dp)) }
//                // Liked Collections
                item { LikedCollectionsSection() }
                item { Spacer(modifier = Modifier.height(56.dp)) }
                item { AllSongsSectionHeader() }
                // All Songs
                itemsIndexed(items = songs,
                    key = {_, item -> item.title}
                )
                {index, item->
                    SongRow(song = item, isSelected = item.isPlaying)
                    if (index < songs.size-1){
                        Spacer(modifier = Modifier.height(12.dp))
                    }
            }
            }


        // ── Floating Mini Player (bottom) ─────────────────────────────────
        FloatingMiniPlayer(
            title = nowPlayingTitle,
            artist = nowPlayingArtist,
            isPaused = onMiniPlayerPaused,
            onPlayerClick = onMiniPlayerClick,
            onPlayPauseClick = onMiniPlayerPlayPause,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 22.dp, vertical = 24.dp),
        )
    }
}



// Recently Played
@Composable
private fun RecentlyPlayedSection(onViewHistoryClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        // Section header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Recently Played",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f),
            )
            // "View History" button
            TextButton(
                onClick = onViewHistoryClick,
            ){
                Text(
                    text = "VIEW HISTORY",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        // Horizontal scroll row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // Featured card
            FeaturedRecentCard()

            // Secondary cards
            SecondaryRecentCard(title = "Neon Pulse", genre = "CYBERNETICS",
                colors = MaterialTheme.colorScheme.cardGradient1)
            SecondaryRecentCard(title = "Velvet Void", genre = "LOOMIS",
                colors = MaterialTheme.colorScheme.cardGradient2)
            SecondaryRecentCard(title = "Quartz Rain", genre = "PRISM",
                colors = MaterialTheme.colorScheme.cardGradient3)
//                colors = listOf(MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.purpleDark)
            ExploreMoreCard()
        }
    }
}

@Composable
private fun FeaturedRecentCard() {
    Box(
        modifier = Modifier
            .width(288.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.tertiary)
                )
            )
    ) {
        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 200f
                    )
                )
        )

        // Bottom info panel
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // "NOW PLAYING" chip
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Neutral800.copy(alpha = 0.4f))
                    .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                    .padding(horizontal = 13.dp, vertical = 3.dp)
            ) {
                Text(
                    text = "NOW PLAYING",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    letterSpacing = 1.sp,
                )
            }

            Text(
                text = "Ethereal Drift",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp
            )

            Text(
                text = "2022",
//                color = MaterialTheme.colorScheme.onSurfaceVariant,
                color = MaterialTheme.colorScheme.textDim,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun SecondaryRecentCard(
    title: String,
    genre: String,
    colors: Brush,
) {
    Box(
        modifier = Modifier
            .width(288.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .background(colors)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp),
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 22.sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = genre,
                color = MaterialTheme.colorScheme.textDim,
                style = MaterialTheme.typography.bodyMedium,

            )
        }
    }
}

@Composable
private fun ExploreMoreCard() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(288.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, Color(0x1A484847), RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //should be a button
            Icon(
                painter = painterResource(id = R.drawable.ic_outline_arrow_drop_down_circle),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "EXPLORE MORE",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

// ─── Liked Collections ────────────────────────────────────────────────────────
@Composable
private fun LikedCollectionsSection() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Text(
            text = "Liked Collections",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.6).sp,
            lineHeight = 32.sp,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(336.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            LikedSongsCard()
            MosaicCollectionCard(
                title = "Daily Discoveries",
                subtitle = "Personalized for you",
            )
            AmbientLibraryCard()
        }
    }
}

//this should be clickable
@Composable
private fun LikedSongsCard() {
    Column(modifier = Modifier.width(256.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(241.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.linearGradient(colors = listOf(MaterialTheme.colorScheme.tertiary, MaterialTheme.colorScheme.pinkDark))
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart_check),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Liked Songs", color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge, fontSize = 20.sp)
        Text(text = "1,248 Tracks", color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall, fontSize = 14.sp)
    }
}

@Composable
private fun MosaicCollectionCard(title: String, subtitle: String) {
    Column(modifier = Modifier.width(256.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(8.dp))
                        .background(Brush.radialGradient(listOf(MaterialTheme.colorScheme.accentTeal.copy(0.7f), Color.Transparent))))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(8.dp))
                        .background(Brush.radialGradient(listOf(MaterialTheme.colorScheme.secondary.copy(0.7f), Color.Transparent))))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(8.dp))
                        .background(Brush.radialGradient(listOf(MaterialTheme.colorScheme.primary.copy(0.7f), Color.Transparent))))
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(8.dp))
                        .background(Brush.radialGradient(listOf(MaterialTheme.colorScheme.pinkLight.copy(0.7f), Color.Transparent))))
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.8f))
                        .border(1.dp, MaterialTheme.colorScheme.selectedBorder, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_play),
                        contentDescription = "Play",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
    }
}

@Composable
private fun AmbientLibraryCard() {
    Column(modifier = Modifier.width(256.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(MaterialTheme.colorScheme.accentTeal.copy(0.4f), MaterialTheme.colorScheme.primary.copy(0.4f))
                        )
                    )
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_media_play),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Deep Focus Sessions",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 25.sp,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Ambient Library", color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(text = "42 Collections", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 14.sp)
    }
}

// All Songs
@Composable
private fun AllSongsSectionHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "All Songs",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.weight(1f),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SmallIconButton(iconResId = R.drawable.ic_date_range, description = "Sort")
            SmallIconButton(iconResId = R.drawable.ic_sort_by_alpha, description = "Filter")
        }
    }
}





// ─── Floating Mini Player ────────────────────────────────────────────────────
@Composable
private fun FloatingMiniPlayer(
    title: String,
    artist: String,
    isPaused: Boolean,
    onPlayerClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.glassBackground)
            .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
            .padding(horizontal = 25.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.tertiary)
                    )
                )
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp,
            )
            Text(
                text = artist.uppercase(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                letterSpacing = 0.55.sp,
                lineHeight = 16.5.sp,
            )
        }

        IconButton(onClick = onPlayPauseClick, modifier = Modifier.size(36.dp)) {
            Icon(
                painter = painterResource(
                    id = if (isPaused)R.drawable.ic_play_arrow
                    else R.drawable.ic_pause
                ),
                contentDescription = "Play / Pause",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ─── Preview Data ─────────────────────────────────────────────────────────────
private fun previewSongs() = listOf(
    SongItem("Shadow of the Colossus", "Yuka Kitamura", "Echoes of the…", isLiked = true),
    SongItem("Ultraviolet Memories", "Kavinsky", "Reborn"),
    SongItem("Borealis", "Ludovico Einaudi", "Seven Days…", isLiked = true),
    SongItem("Cerulean Sky", "Bonobo", "Fragments"),
    SongItem("Ethereal Drift", "Solaris Collective", "Digital Dreams", isPlaying = true),
)

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E)
@Composable
fun MusicLibraryScreenPreview() {
    AppTheme {
        MusicLibraryScreen(onMiniPlayerPaused = false)
    }
}


// scaffold top bar, RecentlyPlayed Header Text, Start populating data from DB