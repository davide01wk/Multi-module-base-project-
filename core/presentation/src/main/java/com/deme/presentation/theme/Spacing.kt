package com.deme.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 32.dp,
    val xl: Dp = 64.dp,
    val xxl: Dp = 128.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }
