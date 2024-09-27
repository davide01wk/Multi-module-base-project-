package com.deme.presentation.tracker_overview

import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    data class OnAddFoodClick(val mealType: MealType): TrackerOverviewEvent()
    data class OnDeleteTrackedFood(val food: TrackedFood): TrackerOverviewEvent()
    object OnNextDayClick: TrackerOverviewEvent()
    object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnMealExpand(val mealType: MealType): TrackerOverviewEvent()
}