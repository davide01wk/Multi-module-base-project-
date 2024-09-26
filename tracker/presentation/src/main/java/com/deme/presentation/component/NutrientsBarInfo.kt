package com.deme.presentation.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.deme.core.presentation.R
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.theme.ProteinColor

@Composable
fun NutrientsBarInfo(
    modifier: Modifier = Modifier,
    goal: Int,
    value: Int,
    name: String,
    color: Color,
    strokeWidth: Dp = 8.dp,
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error

    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = value) {
        angleRatio.animateTo(
            targetValue = if (goal > 0) value / goal.toFloat() else 0f,
            animationSpec = tween(durationMillis = 300)
        )
    }
    Box(
        modifier =
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier.align(Alignment.Center)) {
            if(value <= goal){
                drawCircle(
                    color = background,
                    center = center,
                    radius = size.height / 2,
                    style = Stroke(width = strokeWidth.toPx())
                )
                drawArc(
                    color = color,
                    size = size,
                    sweepAngle = angleRatio.value * 360f,
                    startAngle = 90f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth.toPx())
                )
            }else {
                drawCircle(
                    color = caloriesExceedColor,
                    center = center,
                    radius = size.height / 2,
                    style = Stroke(width = strokeWidth.toPx())
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize()
        ){
            UnitDisplay(
                value = value.toString(),
                unit = stringResource(R.string.grams),
                unitTextColor = Color.White,
                valueTextColor = Color.White
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.xs))
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Light,
                fontSize = LocalFontSize.current.fontSize14
            )
        }
    }
}

@Composable
@Preview
private fun NutrientsBarInfoPreview() {
    CaloryTrackerTheme {
        NutrientsBarInfo(
            modifier = Modifier.size(90.dp),
            goal = 700,
            value = 200,
            name = "Proteins",
            color = ProteinColor
        )
    }
}