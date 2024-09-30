package com.deme.presentation.gender

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
import com.deme.domain.model.Gender
import com.deme.presentation.components.ActionButton
import com.deme.presentation.components.SelectableButton
import com.deme.presentation.util.UiEvent
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing


@Composable
fun GenderRoute(
    onNavigateToAge: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNavigateToAge()
                else -> {}
            }
        }
    }
    GenderScreen(
        selectedGender = viewModel.selectedGender,
        onGenderClick = viewModel::onGenderClick,
        onNextClick = viewModel::onNextClick
    )
}

@Composable
fun GenderScreen(
    selectedGender: Gender,
    onGenderClick: (Gender) -> Unit,
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.md))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = selectedGender is Gender.Male,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.size(LocalSpacing.current.md))

                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = selectedGender is Gender.Female,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGenderClick(Gender.Female)
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
fun GenderScreenPreview() {
    CaloryTrackerTheme {
        GenderScreen(
            selectedGender = Gender.Male,
            onGenderClick = {},
            onNextClick = {}
        )
    }
}