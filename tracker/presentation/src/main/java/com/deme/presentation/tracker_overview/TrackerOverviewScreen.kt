package com.deme.presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deme.core.presentation.R
import com.deme.presentation.component.AddButton
import com.deme.presentation.component.DaySelector
import com.deme.presentation.component.ExpandableMealCard
import com.deme.presentation.component.NutrientHeader
import com.deme.presentation.component.TrackedFoodCard
import com.deme.presentation.theme.CaloryTrackerTheme
import com.deme.presentation.theme.LocalSpacing
import com.deme.presentation.util.UiEvent

@Composable
fun TrackerOverviewRoute(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigateToSearch: (UiEvent.Navigate) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigateToSearch(event)
                else -> Unit
            }
        }
    }

    TrackerOverviewScreen(
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun TrackerOverviewScreen(
    state: TrackerOverviewState,
    onEvent: (TrackerOverviewEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = LocalSpacing.current.md)
    ) {
        item {
            NutrientHeader(state = state)
            Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
            DaySelector(
                modifier = Modifier.fillMaxWidth(),
                onPreviousDayClick = {
                    onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                localdate = state.date
            )
            Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
            Column {
                state.meals.forEach { meal ->
                    ExpandableMealCard(
                        modifier = Modifier.height(100.dp),
                        meal = meal,
                        onExpandClick = {
                            onEvent(TrackerOverviewEvent.OnMealExpand(mealType = meal.mealType))
                        },
                        content = {
                            meal.trackedFood.forEach { trackedFood ->
                                TrackedFoodCard(
                                    modifier = Modifier.height(100.dp),
                                    food = trackedFood,
                                    onDeleteClick = {
                                        onEvent(TrackerOverviewEvent.OnDeleteTrackedFood(food = trackedFood))
                                    }
                                )
                            }
                            AddButton(
                                text = stringResource(
                                    id = R.string.add_meal,
                                    meal.name.asString(LocalContext.current)
                                ),
                                onClick = {
                                    onEvent(TrackerOverviewEvent.OnAddFoodClick(mealType = meal.mealType))
                                }
                            )
                        }
                    )
                    Spacer(modifier = Modifier.size(LocalSpacing.current.sm))
                }
            }
        }
    }
}


@Composable
@Preview
private fun TrackerOverviewScreenPreview(){
    CaloryTrackerTheme {
        TrackerOverviewScreen(state = TrackerOverviewState.default(), onEvent = {})
    }
}
