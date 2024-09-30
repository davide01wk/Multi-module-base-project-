package com.deme.presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.CarbColor
import com.deme.presentation.theme.FatColor
import com.deme.presentation.theme.ProteinColor

@Composable
fun NutrientsBar(
    modifier: Modifier = Modifier,
    calorieGoal: Int,
    calories: Int,
    fat: Int,
    proteins: Int,
    carbs: Int
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = proteins) {
        proteinWidthRatio.animateTo(
            targetValue = ((proteins * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRatio.animateTo(
            targetValue = ((fat * 9f) / calorieGoal)
        )
    }
    Box {
        Canvas(modifier = modifier) {
            if(calories <= calorieGoal){
                val carbsWidth = carbWidthRatio.value * size.width
                val fatWidth = fatWidthRatio.value * size.width
                val proteinWidth = proteinWidthRatio.value * size.width

                drawRoundRect(
                    color = background,
                    size = this.size,
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = FatColor,
                    size = Size(height = size.height, width = carbsWidth + proteinWidth + fatWidth),
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = ProteinColor,
                    size = Size(height = size.height, width = carbsWidth + proteinWidth),
                    cornerRadius = CornerRadius(100f)
                )
                drawRoundRect(
                    color = CarbColor,
                    size = Size(height = size.height, width = carbsWidth),
                    cornerRadius = CornerRadius(100f)
                )
            } else {
                drawRoundRect(
                    color = caloriesExceedColor,
                    size = this.size,
                    cornerRadius = CornerRadius(100f)
                )
            }
        }
    }
}
@Composable
@Preview
private fun NutrientsBarPreview(){
    CaloryTrackerTheme {
        NutrientsBar(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            calorieGoal = 2500,
            calories = 125,
            fat = 5,
            carbs = 15,
            proteins = 5
        )
    }
}