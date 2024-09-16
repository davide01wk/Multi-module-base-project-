package com.deme.presentation.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


data class FontSize(
    val fontSize10: TextUnit = 10.sp,
    val fontSize12: TextUnit = 12.sp,
    val fontSize14: TextUnit = 14.sp,
    val fontSize16: TextUnit = 16.sp,
    val fontSize18: TextUnit = 18.sp,
    val fontSize20: TextUnit = 20.sp,
    val fontSize22: TextUnit = 22.sp,
    val fontSize24: TextUnit = 24.sp,
    val fontSize26: TextUnit = 26.sp,
    val fontSize28: TextUnit = 28.sp,
    val fontSize70: TextUnit = 70.sp,
)

val LocalFontSize = compositionLocalOf { FontSize() }

