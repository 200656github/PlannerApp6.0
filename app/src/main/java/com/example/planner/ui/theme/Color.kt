package com.example.planner.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Soft Pastel Palette — 柔和低饱和度
 *
 * A cohesive, low-saturation scheme built on a periwinkle primary, sage
 * secondary, and dusty-rose tertiary, resting on warm lavender-white neutrals.
 * All tones share a gentle chroma so the whole app reads as one harmonious
 * surface. Val names are retained from the original palette so existing screens
 * pick up the new look without import churn; the semantic role each plays is
 * noted in the comments.
 */

// Neutrals — warm lavender-white surfaces
val WarmBackground = Color(0xFFF6F4F9)        // app background
val WarmSurface = Color(0xFFFCFAFF)           // base surface
val WarmElevated = Color(0xFFFFFFFF)          // elevated cards
val WarmOnSurface = Color(0xFF1B1A22)         // primary text / icons
val WarmOnSurfaceVariant = Color(0xFF6A6677)  // secondary text
val WarmOutline = Color(0xFFCAC4D4)           // hairlines / unfocused borders
val WarmOutlineFocused = Color(0xFF747BC6)    // focused borders use the primary tone

// Primary — Periwinkle (calm, focused)
val OchrePrimary = Color(0xFF747BC6)
val OchreOnPrimary = Color(0xFFFFFFFF)
val OchreContainer = Color(0xFFE4E2F8)
val OchreOnContainer = Color(0xFF2C2E63)

// Secondary — Sage (organic, calm)
val SageSecondary = Color(0xFF8AAE94)
val SageOnSecondary = Color(0xFFFFFFFF)
val SageContainer = Color(0xFFDCEBDD)
val SageOnContainer = Color(0xFF263828)

// Tertiary — Dusty Rose (warm accent)
val ClayTertiary = Color(0xFFD29BA3)
val ClayOnTertiary = Color(0xFFFFFFFF)
val ClayContainer = Color(0xFFF8E0E3)
val ClayOnContainer = Color(0xFF4A2630)

// Priority accents (low-saturation, palette-cohesive)
val PriorityLowColor = Color(0xFF8FA8AB)      // soft teal
val PriorityMediumColor = OchrePrimary        // periwinkle
val PriorityHighColor = Color(0xFFB85F65)     // muted rose
val CompletedTint = Color(0xCC8AAE94)         // sage @ 80%

// Error — muted rose-red, readable but soft
val WarmError = Color(0xFFC76B6E)
