package com.deme.presentation.height

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
fun HeightRoute(
    scaffoldState: ScaffoldState,
    onNavigateToWeight: () -> Unit,
    viewModel: HeightViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNavigateToWeight()
                is UiEvent.ShowSnackbar -> scaffoldState.snackbarHostState.showSnackbar(
                    message = event.message.asString(context)
                )
                else -> {}
            }
        }
    }
    HeightScreen(
        height = viewModel.height,
        onNextClick = viewModel::onNextClick,
        onHeightEnter = viewModel::onHeightEnter
    )
}

@Composable
fun HeightScreen(
    height: String,
    onNextClick: () -> Unit,
    onHeightEnter: (String) -> Unit
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
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )
            UnitTextField(
                value = height,
                onValueChange = onHeightEnter,
                unit = stringResource(id = R.string.cm)
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
        HeightScreen (
            height = "165",
            onNextClick = {},
            onHeightEnter = {}
        )
    }
}