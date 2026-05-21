package com.example.sokasinging.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R

// ─── Design Tokens ───────────────────────────────────────────────────────────
// Extracted from Figma: Soka Player / Now Playing
private val BackgroundColor     = Color(0xFF0E0E0E)
private val AccentLavender      = Color(0xFFE4D7FD)
private val AccentCyan          = Color(0xFF58E7FB)
private val AccentPink          = Color(0xFFFF6C95)
private val AccentTeal          = Color(0xFF006874)
private val TextPrimary         = Color(0xFFE4D7FD)
private val TextSecondary       = Color(0xFFADAAAA)
private val GlassBackground     = Color(0x99262626)   // rgba(38,38,38,0.6)
private val GlassBorder         = Color(0x26484847)   // rgba(72,72,71,0.15)
private val LyricsDimmed40      = Color(0x66ADAAAA)   // rgba(173,170,170,0.4)
private val LyricsDimmed60      = Color(0x99ADAAAA)   // rgba(173,170,170,0.6)
private val SeekBarTrack        = Color(0xFF262626)
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Now Playing screen for Soka Player.
 *
 * Structure:
 *  ┌─────────────────────────┐
 *  │  TopAppBar              │  (song title + artist, hamburger, overflow)
 *  │  Lyrics Canvas          │  (scrollable lyrics, highlighted current line)
 *  │  Playback Controller    │  (glassmorphism card: seek bar + controls)
 *  └─────────────────────────┘
 *
 * Background: solid #0E0E0E with decorative corner radial gradients + a
 * blurred mesh-gradient orb centred behind the lyrics.
 */
@Composable
fun NowPlayingScreen(
    songTitle: String = "Neon Horizon",
    artistName: String = "Celestial Echoes",
    currentTimeLabel: String = "2:45",
    totalTimeLabel: String = "4:12",
    // 0f..1f
    progress: Float = 0.65f,
    onBackClick: () -> Unit = {},
    onOverflowClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onPlayPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onShuffleClick: () -> Unit = {},
    onRepeatClick: () -> Unit = {},
    onVolumeClick: () -> Unit = {},
    onQueueClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        // ── Decorative background gradients (corner radial glows) ──────────
        DecorativeBackground()

        // ── Hidden mesh orb behind lyrics ──────────────────────────────────
        MeshVisualizerOrb(
            modifier = Modifier
                .size(312.dp)
                .align(Alignment.Center)
        )

        // ── Content ────────────────────────────────────────────────────────
        Column(modifier = Modifier.fillMaxSize()) {

            // Top App Bar
            NowPlayingTopBar(
                songTitle = songTitle,
                artistName = artistName,
                onBackClick = onBackClick,
                onOverflowClick = onOverflowClick,
            )

            // Lyrics Canvas (fills remaining space above controller)
            LyricsCanvas(modifier = Modifier.weight(1f))

            // Playback Controller (glassmorphism card)
            PlaybackController(
                currentTimeLabel = currentTimeLabel,
                totalTimeLabel = totalTimeLabel,
                progress = progress,
                onPreviousClick = onPreviousClick,
                onPlayPauseClick = onPlayPauseClick,
                onNextClick = onNextClick,
                onShuffleClick = onShuffleClick,
                onRepeatClick = onRepeatClick,
                onVolumeClick = onVolumeClick,
                onQueueClick = onQueueClick,
                modifier = Modifier.padding(horizontal = 22.dp, vertical = 16.dp),
            )
        }
    }
}

// ─── Decorative Background ────────────────────────────────────────────────────
@Composable
private fun DecorativeBackground() {
    // Four corner radial glows replicated from Figma's "Background Decorative Elements"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(40.dp)
    ) {
        // Top-left: lavender glow
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopStart)
                .background(
                    Brush.radialGradient(
                        colors = listOf(AccentLavender.copy(alpha = 0.15f), Color.Transparent)
                    )
                )
        )
        // Top-right: teal glow
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(AccentTeal.copy(alpha = 0.15f), Color.Transparent)
                    )
                )
        )
        // Bottom-right: pink glow
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomEnd)
                .background(
                    Brush.radialGradient(
                        colors = listOf(AccentPink.copy(alpha = 0.15f), Color.Transparent)
                    )
                )
        )
        // Bottom-left: cyan glow
        Box(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.BottomStart)
                .background(
                    Brush.radialGradient(
                        colors = listOf(AccentCyan.copy(alpha = 0.15f), Color.Transparent)
                    )
                )
        )
    }
}

// ─── Mesh Visualizer Orb ──────────────────────────────────────────────────────
@Composable
private fun MeshVisualizerOrb(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .blur(60.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(AccentPink, AccentTeal, Color(0xFFA79BBE))
                ),
                shape = CircleShape
            )
            .clip(CircleShape)
    )
}

// ─── Top App Bar ──────────────────────────────────────────────────────────────
@Composable
private fun NowPlayingTopBar(
    songTitle: String,
    artistName: String,
    onBackClick: () -> Unit,
    onOverflowClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .padding(horizontal = 22.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Hamburger / back button
        IconButton(onClick = onBackClick, modifier = Modifier.size(32.dp)) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                contentDescription = "Back",
                tint = TextPrimary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        // Song + artist labels
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = songTitle,
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.6).sp,
                lineHeight = 32.sp,
            )
            Text(
                text = artistName,
                color = TextSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 20.sp,
            )
        }

        // Overflow (three-dot) button
        IconButton(onClick = onOverflowClick, modifier = Modifier.size(50.dp)) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_more),
                contentDescription = "More options",
                tint = TextPrimary,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

// ─── Lyrics Canvas ────────────────────────────────────────────────────────────
/**
 * Displays the current lyrics lines with:
 * - Dimmed lines at 40 % / 60 % opacity for surrounding context
 * - A highlighted current line with a subtle glow shadow
 */
@Composable
private fun LyricsCanvas(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        // Line 1 – small / dimmed
        Text(
            text = "In the pulse of the digital rain",
            color = LyricsDimmed40,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 39.sp,
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Line 2 – medium / dimmed
        Text(
            text = "Searching for shadows that remain",
            color = LyricsDimmed60,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 48.75.sp,
        )

        Spacer(modifier = Modifier.height(2.dp))

        // ── Highlighted current line ──────────────────────────────────────
        // Figma: ExtraBold, 36 sp, #E4D7FD, letter-spacing −0.9 sp
        // Shadow: 0 0 15px rgba(228,215,253,0.3)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 17.dp)
        ) {
            Text(
                text = "We are the ghosts of\na future past",
                color = TextPrimary,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-0.9).sp,
                lineHeight = 45.sp,
            )
        }

        // Line 4 – medium / dimmed
        Text(
            text = "Bound by the signals we broadcast",
            color = LyricsDimmed60,
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 48.75.sp,
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Line 5 – small / dimmed
        Text(
            text = "Echoes through the silicon main",
            color = LyricsDimmed40,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 39.sp,
        )
    }
}

// ─── Playback Controller ──────────────────────────────────────────────────────
/**
 * Glassmorphism card containing:
 *   • Seek bar (time labels + progress track)
 *   • Main playback buttons (prev / play-pause pill / next)
 *   • Secondary controls (shuffle, repeat)
 *   • Volume & queue extras
 */
@Composable
private fun PlaybackController(
    currentTimeLabel: String,
    totalTimeLabel: String,
    progress: Float,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onShuffleClick: () -> Unit,
    onRepeatClick: () -> Unit,
    onVolumeClick: () -> Unit,
    onQueueClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(GlassBackground)
            // Border emulated via a drawn outline
            .padding(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(GlassBackground)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            // ── Seek Bar ─────────────────────────────────────────────────
            SeekBar(
                currentTimeLabel = currentTimeLabel,
                totalTimeLabel = totalTimeLabel,
                progress = progress,
            )

            // ── Main Playback Buttons ─────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Previous
                IconButton(onClick = onPreviousClick) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_previous),
                        contentDescription = "Previous",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(40.dp))

                // Play / Pause pill button (signature pill – 80 dp, gradient fill)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(AccentLavender, Color(0xFFD6C9EE)),
                                // 135 deg
                            )
                        )
                ) {
                    IconButton(onClick = onPlayPauseClick) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_media_pause),
                            contentDescription = "Play / Pause",
                            tint = Color(0xFF1A1A1A),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(40.dp))

                // Next
                IconButton(onClick = onNextClick) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_media_next),
                        contentDescription = "Next",
                        tint = TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // ── Secondary Controls ────────────────────────────────────────
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Shuffle
                IconButton(onClick = onShuffleClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_songs),
                        contentDescription = "Shuffle",
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                }
                // Repeat
                IconButton(onClick = onRepeatClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_rotate),
                        contentDescription = "Repeat",
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // ── Volume & Extras ───────────────────────────────────────────
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onVolumeClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_lock_silent_mode_off),
                        contentDescription = "Volume",
                        tint = TextSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
                IconButton(onClick = onQueueClick, modifier = Modifier.size(32.dp)) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_sort_by_size),
                        contentDescription = "Queue",
                        tint = TextSecondary,
                        modifier = Modifier.size(19.dp)
                    )
                }
            }
        }
    }
}

// ─── Seek Bar ─────────────────────────────────────────────────────────────────
@Composable
private fun SeekBar(
    currentTimeLabel: String,
    totalTimeLabel: String,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Time labels
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = currentTimeLabel,
                color = TextPrimary,
                fontSize = 11.sp,
                letterSpacing = 1.1.sp,
                lineHeight = 16.5.sp,
            )
            Text(
                text = totalTimeLabel,
                color = TextSecondary,
                fontSize = 11.sp,
                letterSpacing = 1.1.sp,
                lineHeight = 16.5.sp,
            )
        }

        // Progress track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(CircleShape)
                .background(SeekBarTrack)
        ) {
            // Filled portion – gradient lavender → cyan
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(AccentLavender, AccentCyan)
                        )
                    )
            )
        }
    }
}

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, backgroundColor = 0xFF0E0E0E)
@Composable
fun NowPlayingScreenPreview() {
    NowPlayingScreen()
}
