package com.deme.presentation.tracker_overview

import com.deme.domain.model.MealType
import com.deme.domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    data class onAddFoodClick(val mealType: MealType): TrackerOverviewEvent()
    data class onDeleteTrackedFood(val food: TrackedFood): TrackerOverviewEvent()
    object onNextDayClick: TrackerOverviewEvent()
    object onPreviousDayClick: TrackerOverviewEvent()
    data class onMealExpand(val mealType: MealType): TrackerOverviewEvent()
}