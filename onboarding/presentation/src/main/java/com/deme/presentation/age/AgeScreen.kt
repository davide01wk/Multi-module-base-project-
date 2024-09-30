package com.deme.presentation.age

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
fun AgeRoute(
    scaffoldState: ScaffoldState,
    onNavigateToHeight: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNavigateToHeight()
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message.asString(context)
                )
                else -> {}
            }
        }
    }
    AgeScreen(
        age = viewModel.age,
        onNextClick = viewModel::onNextClick,
        onAgeEnter = viewModel::onAgeEnter
    )
}

@Composable
fun AgeScreen(
    age: String,
    onNextClick: () -> Unit,
    onAgeEnter: (String) -> Unit
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
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.h3
            )
            UnitTextField(
                value = age,
                onValueChange = onAgeEnter,
                unit = stringResource(id = R.string.years)
            )
        }

        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            isEnabled = true,
            onClick = onNextClick
        )
    }
}

@Composable
@Preview
fun GenderScreenPreview() {
    CaloryTrackerTheme {
        AgeScreen(
            age = "20",
            onNextClick = {},
            onAgeEnter = {}
        )
    }
}