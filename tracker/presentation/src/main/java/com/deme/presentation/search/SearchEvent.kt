package com.deme.presentation.search

import com.deme.domain.model.MealType
import com.deme.domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    object OnSearchFood: SearchEvent()
    data class OnExpandFood(val food: TrackableFood): SearchEvent()
    data class OnTrackFoodClick(
        val date: LocalDate,
        val mealType: MealType,
        val food: TrackableFood
    ): SearchEvent()
    data class OnAmountFoodChange(
        val food: TrackableFood,
        val amount: String
    ): SearchEvent()
    data class OnQueryChange(val query: String): SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent() //todo understand this
}