package com.deme.presentation.activity

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
import com.deme.domain.model.ActivityLevel
import com.deme.presentation.components.ActionButton
import com.deme.presentation.components.SelectableButton
import com.deme.presentation.util.UiEvent
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing


@Composable
fun ActivityLevelRoute(
    onNavigateToGoal: (UiEvent.Navigate) -> Unit,
    viewModel: ActivityLevelViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigateToGoal(event)
                else -> {}
            }
        }
    }
    ActivityLevelScreen(
        selectedActivityLevel = viewModel.selectedActivityLevel,
        onActivityLevelClick = viewModel::onActivityLevelClick,
        onNextClick = viewModel::onNextClick
    )
}

@Composable
fun ActivityLevelScreen(
    selectedActivityLevel: ActivityLevel,
    onActivityLevelClick: (ActivityLevel) -> Unit,
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
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = selectedActivityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelClick(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.md))

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelClick(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.md))

                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelClick(ActivityLevel.High)
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
fun ActivityLevelScreenPreview() {
    CaloryTrackerTheme {
        ActivityLevelScreen(
            selectedActivityLevel = ActivityLevel.Medium,
            onActivityLevelClick = {},
            onNextClick = {}
        )
    }
}