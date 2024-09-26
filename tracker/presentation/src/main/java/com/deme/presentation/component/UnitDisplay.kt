package com.deme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.theme.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    value: String,
    unit: String,
    valueTextColor: Color = MaterialTheme.colors.primary,
    valueTextSize: TextUnit = LocalFontSize.current.fontSize20,
    unitTextColor: Color = MaterialTheme.colors.primary,
    unitTextSize: TextUnit = LocalFontSize.current.fontSize14,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            style = TextStyle(
                color = valueTextColor,
                fontSize = valueTextSize
            ),
            modifier = Modifier
                .width(IntrinsicSize.Min) // wrap content
                .alignBy(LastBaseline)
        )
        Spacer(modifier = Modifier.size(LocalSpacing.current.xs))
        Text(
            modifier = Modifier.alignBy(LastBaseline),
            style = TextStyle(
                color = unitTextColor,
                fontSize = unitTextSize
            ),
            text = unit
        )
    }
}

@Composable
@Preview
private fun UnitDisplayPreview() {
    CaloryTrackerTheme {
        UnitDisplay(
            value = "1200",
            unit = "kcal",
            valueTextColor = Color.White,
            valueTextSize = LocalFontSize.current.fontSize26,
            unitTextColor = Color.White,
            unitTextSize = LocalFontSize.current.fontSize10
        )
    }
}