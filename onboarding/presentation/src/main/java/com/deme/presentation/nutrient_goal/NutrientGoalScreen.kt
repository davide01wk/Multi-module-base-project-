package com.deme.presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.deme.core.presentation.R
import com.deme.presentation.components.ActionButton
import com.deme.presentation.components.UnitTextField
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.util.UiEvent

@Composable
fun NutrientGoalRoute(
    scaffoldState: ScaffoldState,
    onNavigateToTracker: (UiEvent.Navigate) -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigateToTracker(event)
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message.asString(context)
                )

                else -> {}
            }
        }
    }
    NutrientGoalScreen(
        nutrientGoalState = viewModel.nutrientGoal,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun NutrientGoalScreen(
    nutrientGoalState: NutrientGoalState,
    onEvent: (NutrientGoalEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.lg)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.h3
            )
            UnitTextField(
                value = nutrientGoalState.carbRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnCarbRatioEnter(carbRatio = it)) },
                unit = stringResource(id = R.string.percent_carbs)
            )
            UnitTextField(
                value = nutrientGoalState.proteinRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnProteinRatioEnter(proteinRatio = it)) },
                unit = stringResource(id = R.string.percent_proteins)
            )
            UnitTextField(
                value = nutrientGoalState.fatRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnFatRatioEnter(fatRatio = it)) },
                unit = stringResource(id = R.string.percent_fats)
            )

        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            isEnabled = true,
            onClick = { onEvent(NutrientGoalEvent.OnNextClick) }
        )
    }
}

@Composable
@Preview
fun GenderScreenPreview() {
    CaloryTrackerTheme {
        NutrientGoalScreen(
            nutrientGoalState = NutrientGoalState(),
            onEvent = {}
        )
    }
}