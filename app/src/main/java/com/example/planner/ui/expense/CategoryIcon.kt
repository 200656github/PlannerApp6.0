package com.example.planner.ui.expense

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.planner.domain.model.ExpenseCategory

val ExpenseCategory.iconVector: ImageVector
    get() = when (this) {
        ExpenseCategory.FOOD -> Icons.Rounded.Restaurant
        ExpenseCategory.TRANSPORT -> Icons.Rounded.DirectionsBus
        ExpenseCategory.SHOPPING -> Icons.Rounded.ShoppingBag
        ExpenseCategory.ENTERTAINMENT -> Icons.Rounded.MusicNote
        ExpenseCategory.HOUSING -> Icons.Rounded.Home
        ExpenseCategory.MEDICAL -> Icons.Rounded.LocalHospital
        ExpenseCategory.EDUCATION -> Icons.Rounded.School
        ExpenseCategory.OTHER -> Icons.Rounded.MoreHoriz
    }

/**
 * Cohesive low-saturation accent per category, used by the monthly overview
 * (stacked bar + legend) and the expense list icons. All tones stay within the
 * pastel chroma of the app palette.
 */
val ExpenseCategory.themeColor: Color
    get() = when (this) {
        ExpenseCategory.FOOD -> Color(0xFFE0A074)          // soft apricot
        ExpenseCategory.TRANSPORT -> Color(0xFF7E9CB8)     // soft blue
        ExpenseCategory.SHOPPING -> Color(0xFFC28FB5)      // soft mauve
        ExpenseCategory.ENTERTAINMENT -> Color(0xFF8FB39A) // soft sage
        ExpenseCategory.HOUSING -> Color(0xFFB59CC2)       // soft lavender
        ExpenseCategory.MEDICAL -> Color(0xFFD49BA0)       // dusty rose
        ExpenseCategory.EDUCATION -> Color(0xFF9CB5B8)     // soft teal
        ExpenseCategory.OTHER -> Color(0xFFB0B0B8)         // soft gray
    }
