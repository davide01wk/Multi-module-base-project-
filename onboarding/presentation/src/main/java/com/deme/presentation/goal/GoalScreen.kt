package com.deme.presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.deme.core.presentation.R
import com.deme.domain.model.GoalType
import com.deme.presentation.components.ActionButton
import com.deme.presentation.components.SelectableButton
import com.deme.presentation.util.UiEvent
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing


@Composable
fun GoalRoute(
    onNavigateToNutrientGoal: (UiEvent.Navigate) -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigateToNutrientGoal(event)
                else -> {}
            }
        }
    }
    GoalScreen(
        selectedGoal = viewModel.selectedGoal,
        onGoalClick = viewModel::onGoalClick,
        onNextClick = viewModel::onNextClick
    )
}

@Composable
fun GoalScreen(
    selectedGoal: GoalType,
    onGoalClick: (GoalType) -> Unit,
    onNextClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(LocalSpacing.current.lg)) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = selectedGoal is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGoalClick(GoalType.LoseWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.md))

                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = selectedGoal is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGoalClick(GoalType.KeepWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.md))

                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = selectedGoal is GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGoalClick(GoalType.GainWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            isEnabled = true,
            onClick = {
                onNextClick()
            }
        )
    }
}

@Composable
@Preview
fun GoalScreenPreview() {
    CaloryTrackerTheme {
        GoalScreen(
            selectedGoal = GoalType.KeepWeight,
            onGoalClick = {},
            onNextClick = {}
        )
    }
}