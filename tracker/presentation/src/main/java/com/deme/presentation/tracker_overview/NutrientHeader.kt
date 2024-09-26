package com.deme.presentation.tracker_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deme.core.presentation.R
import com.deme.presentation.component.NutrientsBar
import com.deme.presentation.component.NutrientsBarInfo
import com.deme.presentation.component.UnitDisplay
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalFontSize
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.theme.ProteinColor

@Composable
fun NutrientHeader(
    modifier: Modifier = Modifier,
    state: TrackerOverviewState,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = LocalSpacing.current.md,
                vertical = LocalSpacing.current.xl
            )

        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            UnitDisplay(
                value = state.totalCalories.toString(),
                unit = stringResource(R.string.kcal),
                valueTextColor = Color.White,
                unitTextColor = Color.White
            )
            Column {
                Text(
                    text = stringResource(R.string.your_goal),
                    color = Color.White
                )
                UnitDisplay(
                    value = state.caloriesGoal.toString(),
                    unit = stringResource(R.string.kcal),
                    valueTextColor = Color.White,
                    unitTextColor = Color.White
                )
            }
        }
        Spacer(modifier = Modifier.size(LocalSpacing.current.md))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NutrientsBar(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                calorieGoal = state.caloriesGoal,
                calories = state.totalCalories,
                fat = state.fatGoal,
                proteins = state.proteinGoal,
                carbs = state.carbsGoal
            )
        }
        Spacer(modifier = Modifier.size(LocalSpacing.current.md))
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NutrientsBarInfo(
                modifier = Modifier.size(90.dp),
                goal = state.proteinGoal,
                value = state.totalProtein,
                name = stringResource(R.string.carbs),
                color = ProteinColor
            )
            NutrientsBarInfo(
                modifier = Modifier.size(90.dp),
                goal = state.proteinGoal,
                value = state.totalProtein,
                name = stringResource(R.string.protein),
                color = ProteinColor
            )
            NutrientsBarInfo(
                modifier = Modifier.size(90.dp),
                goal = state.proteinGoal,
                value = state.totalProtein,
                name = stringResource(R.string.fat),
                color = ProteinColor
            )
        }
    }


}
@Composable
@Preview
fun NutrientHeaderPreview(){
    CaloryTrackerTheme {
        NutrientHeader(
            modifier = Modifier.fillMaxWidth(),
            state = TrackerOverviewState.default()
        )
    }
}