package com.example.sokasinging.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// ── Brand / Accent ──────────────────────────────────────────────────────────
val Lavender300   = Color(0xFFE4D7FD)   // Primary accent — highlighted lyrics, buttons
val Lavender200   = Color(0xFFD6C9EE)   // Play button gradient end
val Lavender500   = Color(0xFFA79BBE)   // Muted lavender for subtext
val Cyan300       = Color(0xFF58E7FB)   // Seek bar gradient, teal glow
val Pink400       = Color(0xFFFD3E80)   // Liked songs gradient end
val Pink300       = Color(0xFFFF6C95)   // Liked songs, corner glow
val Pink200       = Color(0xFFFF8FA9)   // Secondary cards
val Teal600       = Color(0xFF006874)   // Corner glow
val Purple900     = Color(0xFF261E3A)   // Dark overlay

// ── Neutrals ─────────────────────────────────────────────────────────────────
val Neutral950    = Color(0xFF0E0E0E)   // Screen background
val Neutral900    = Color(0xFF1A1A1A)   // Surface (cards, selected rows)
val Neutral850    = Color(0xFF20201F)   // Surface 2 (icon buttons)
val Neutral800    = Color(0xFF262626)   // Seek bar track, glass background base

// ── Text ─────────────────────────────────────────────────────────────────────
val TextOnDark    = Color(0xFFFFFFFF)
val TextSubtle    = Color(0xFFADAAAA)   // Artist names, subtitles
val TextDimWhite  = Color(0xB3FFFFFF)   // 70 % white for genre labels

// ── Glass / Overlay ───────────────────────────────────────────────────────────
val GlassSurface  = Color(0x99262626)   // rgba(38,38,38, 0.6)
val GlassBorder   = Color(0x26484847)   // rgba(72,72,71, 0.15)
val SelectedBorder = Color(0x33E4D7FD)  // rgba(228,215,253, 0.2)

// ── Theme Extensions ────────────────────────────────────────────────────────
val ColorScheme.accentTeal: Color get() = Teal600
val ColorScheme.textDim: Color get() = TextDimWhite
val ColorScheme.glassBackground: Color get() = GlassSurface
val ColorScheme.selectedBorder: Color get() = SelectedBorder
val ColorScheme.lavenderLight: Color get() = Lavender200
val ColorScheme.lavenderMuted: Color get() = Lavender500
val ColorScheme.pinkLight: Color get() = Pink200
val ColorScheme.pinkDark: Color get() = Pink400
val ColorScheme.purpleDark: Color get() = Purple900


val ColorScheme.cardGradient1: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFF74B9FF), Color(0xFF0984E3)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )

val ColorScheme.cardGradient2: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFFE17055), Color(0xFF6C5CE7)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )

val ColorScheme.cardGradient3: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFF55EFC4), Color(0xFF00B894)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )
val ColorScheme.cardGradient4: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFFFFD32A), Color(0xFFFF6B35)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )
val ColorScheme.cardGradient5: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFF6C5CE7), Color(0xFFA29BFE)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )
val ColorScheme.cardGradient6: Brush
    get() = Brush.linearGradient(
        listOf(
            Color(0xFF00CEC9), Color(0xFF0984E3)),
        start = Offset(0f, 0f),
        end = Offset(300f, 600f)
    )

val ColorScheme.allGradients : List<Brush>
    get() = listOf(
    cardGradient1,
    cardGradient2,
    cardGradient3,
    cardGradient4,
    cardGradient5,
    cardGradient6
)
