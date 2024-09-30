package com.deme.presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deme.core.presentation.R
import com.deme.domain.model.MealType
import com.deme.presentation.component.NutrientInfo
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.tracker_overview.MealState
import com.deme.presentation.util.UiText


@Composable
fun ExpandableMealCard(
    modifier: Modifier = Modifier,
    meal: MealState,
    onExpandClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.clickable {
            onExpandClick()
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(all = LocalSpacing.current.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(meal.imageRes),
                contentDescription = meal.name.asString(context),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Column(
                modifier = modifier
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = meal.name.asString(context))
                    Icon(
                        imageVector = if (meal.isExpanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = if (meal.isExpanded)
                            stringResource(R.string.collapse)
                        else
                            stringResource(R.string.extend)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UnitDisplay(
                        value = meal.mealCalories.toString(),
                        unit = stringResource(R.string.kcal),
                        valueTextColor = Color.Black,
                        unitTextColor = Color.Black
                    )
                    Row {
                        NutrientInfo(
                            name = stringResource(R.string.carbs),
                            value = meal.mealCarbs.toString(),
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
                        NutrientInfo(
                            name = stringResource(R.string.protein),
                            value = meal.mealProteins.toString(),
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
                        NutrientInfo(
                            name = stringResource(R.string.fat),
                            value = meal.mealFats.toString(),
                            unit = stringResource(R.string.grams)
                        )
                    }
                }
            }
        }
        AnimatedVisibility(meal.isExpanded) {
            Column(
                modifier = Modifier.padding(horizontal = LocalSpacing.current.sm)
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun ExpandableMealCardPreview() {
    CaloryTrackerTheme {
        ExpandableMealCard(
            modifier = Modifier.height(100.dp),
            meal = MealState(
                mealType = MealType.BreakFast,
                name = UiText.StringResource(R.string.breakfast),
                imageRes = R.drawable.ic_breakfast,
                mealCarbs = 77,
                mealProteins = 837,
                mealFats = 95,
                mealCalories = 416,
                isExpanded = true,
                trackedFood = listOf()
            ),
            onExpandClick = {  },
            content = {

            }
        )
    }
}