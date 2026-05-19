package com.example.sokasinging.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sokasinging.R



val ManropeFamily = FontFamily(
    Font(R.font.manrope_regular,   FontWeight.Normal),
    Font(R.font.manrope_bold,      FontWeight.Bold),
    Font(R.font.manrope_extrabold, FontWeight.ExtraBold),
)

val InterFamily = FontFamily(
    Font(R.font.inter_variable,   FontWeight.Normal),
    Font(R.font.inter_variable,    FontWeight.Medium),
    Font(R.font.inter_variable,  FontWeight.SemiBold),
    Font(R.font.inter_variable,      FontWeight.Bold),
)

val AppTypography = Typography(
    // Figma "Heading 1" — song title in TopAppBar
    headlineLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.6).sp,
    ),
    // Figma "Heading 2" — section titles (Recently Played, All Songs)
    headlineMedium = TextStyle(
        fontFamily   = ManropeFamily,
        fontWeight   = FontWeight.Bold,
        fontSize     = 30.sp,
        lineHeight   = 36.sp,
        letterSpacing = (-0.75).sp,
    ),
    // Figma "Heading 3" — card titles
    titleLarge = TextStyle(
        fontFamily   = ManropeFamily,
        fontWeight   = FontWeight.Bold,
        fontSize     = 24.sp,
        lineHeight   = 32.sp,
    ),
    // Figma "Heading 4" — song row titles
    titleMedium = TextStyle(
        fontFamily   = InterFamily,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 16.sp,
        lineHeight   = 24.sp,
    ),
    // Figma body / artist names
    bodyMedium = TextStyle(
        fontFamily   = InterFamily,
        fontWeight   = FontWeight.Normal,
        fontSize     = 14.sp,
        lineHeight   = 20.sp,
    ),
    // Figma small labels / genre chips / time codes
    labelSmall = TextStyle(
        fontFamily   = InterFamily,
        fontWeight   = FontWeight.Normal,
        fontSize     = 11.sp,
        lineHeight   = 16.5.sp,
        letterSpacing = 1.1.sp,
    ),
    // Figma highlighted lyrics line
    displayMedium = TextStyle(
        fontFamily   = ManropeFamily,
        fontWeight   = FontWeight.ExtraBold,
        fontSize     = 36.sp,
        lineHeight   = 45.sp,
        letterSpacing = (-0.9).sp,
    ),
)
