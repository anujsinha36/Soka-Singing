package com.example.sokasinging.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext


private val darkScheme = darkColorScheme(
    primary          = Lavender300,
    onPrimary        = Neutral950,
    primaryContainer = Neutral900,
    secondary        = Cyan300,
    onSecondary      = Neutral950,
    tertiary         = Pink300,
    background       = Neutral950,
    surface          = Neutral900,
    surfaceVariant   = Neutral850,
    onBackground     = TextOnDark,
    onSurface        = TextOnDark,
    onSurfaceVariant = TextSubtle,
    outline          = GlassBorder,
)



@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> darkScheme
  }

  MaterialTheme(
    colorScheme = darkScheme,
    typography = AppTypography,
    content = content
  )
}
