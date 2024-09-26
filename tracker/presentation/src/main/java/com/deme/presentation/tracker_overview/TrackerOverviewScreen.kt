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

@Composable
fun TrackerOverviewRoute(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit
) {
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
                    onEvent(TrackerOverviewEvent.onPreviousDayClick)
                },
                onNextDayClick = {
                    onEvent(TrackerOverviewEvent.onNextDayClick)
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
                            onEvent(TrackerOverviewEvent.onMealExpand(mealType = meal.mealType))
                        },
                        content = {
                            meal.trackedFood.forEach { trackedFood ->
                                TrackedFoodCard(
                                    modifier = Modifier.height(100.dp),
                                    food = trackedFood,
                                    onDeleteClick = {
                                        onEvent(TrackerOverviewEvent.onDeleteTrackedFood(food = trackedFood))
                                    }
                                )
                            }
                            AddButton(
                                text = stringResource(
                                    id = R.string.add_meal,
                                    meal.name.asString(LocalContext.current)
                                ),
                                onClick = {
                                    onEvent(TrackerOverviewEvent.onAddFoodClick(mealType = meal.mealType))
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
