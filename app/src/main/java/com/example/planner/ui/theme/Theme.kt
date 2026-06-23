package com.example.planner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = OchrePrimary,
    onPrimary = OchreOnPrimary,
    primaryContainer = OchreContainer,
    onPrimaryContainer = OchreOnContainer,
    secondary = SageSecondary,
    onSecondary = SageOnSecondary,
    secondaryContainer = SageContainer,
    onSecondaryContainer = SageOnContainer,
    tertiary = ClayTertiary,
    onTertiary = ClayOnTertiary,
    tertiaryContainer = ClayContainer,
    onTertiaryContainer = ClayOnContainer,
    error = WarmError,
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF8DADA),
    onErrorContainer = Color(0xFF5A2226),
    background = WarmBackground,
    onBackground = WarmOnSurface,
    surface = WarmSurface,
    onSurface = WarmOnSurface,
    surfaceVariant = Color(0xFFECE8F4),
    onSurfaceVariant = WarmOnSurfaceVariant,
    surfaceTint = OchrePrimary,
    outline = WarmOutline,
    outlineVariant = Color(0xFFE3DFF0)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFB8BEEC),
    onPrimary = Color(0xFF2A2D5C),
    primaryContainer = Color(0xFF444A8A),
    onPrimaryContainer = Color(0xFFE4E2F8),
    secondary = Color(0xFFA6C8AF),
    onSecondary = Color(0xFF1E2D20),
    secondaryContainer = Color(0xFF364A38),
    onSecondaryContainer = Color(0xFFDCEBDD),
    tertiary = Color(0xFFE0B4BA),
    onTertiary = Color(0xFF441E26),
    tertiaryContainer = Color(0xFF5E363E),
    onTertiaryContainer = Color(0xFFF8E0E3),
    error = Color(0xFFE0A8AB),
    onError = Color(0xFF441E1E),
    errorContainer = Color(0xFF5E3434),
    onErrorContainer = Color(0xFFF8DADA),
    background = Color(0xFF131218),
    onBackground = Color(0xFFE6E1EC),
    surface = Color(0xFF1A1A22),
    onSurface = Color(0xFFE6E1EC),
    surfaceVariant = Color(0xFF23222B),
    onSurfaceVariant = Color(0xFFC9C4D2),
    surfaceTint = Color(0xFFB8BEEC),
    outline = Color(0xFF8E8A99),
    outlineVariant = Color(0xFF46434F)
)

@Composable
fun PlannerTheme(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = WarmTypography,
        content = content
    )
}
