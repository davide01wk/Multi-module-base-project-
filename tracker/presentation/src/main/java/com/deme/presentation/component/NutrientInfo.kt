package com.deme.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.tracker_overview.components.UnitDisplay

@Composable
fun NutrientInfo(
    name: String,
    value: String,
    unit: String,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            value = value,
            unit = unit,
            unitTextColor = Color.Black,
            valueTextColor = Color.Black,
            valueTextSize = LocalFontSize.current.fontSize14,
            unitTextSize = LocalFontSize.current.fontSize10
        )
        Text(
            text = name,
            fontWeight = FontWeight.Light,
            fontSize = LocalFontSize.current.fontSize14
        )
    }
}
@Preview
@Composable
private fun NutrientInfoPreview(){
    CaloryTrackerTheme {
        NutrientInfo(
            name = "Protein", value = "12", unit = "g"
        )
    }
}