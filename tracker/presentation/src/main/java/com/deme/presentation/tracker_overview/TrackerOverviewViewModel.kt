package com.deme.presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deme.domain.preferences.Preferences
import com.deme.domain.usecase.TrackerUseCases
import com.deme.presentation.navigation.Route
import com.deme.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    preferences: Preferences
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewState.default())
        private set

    init {
        preferences.saveShowOnboarding(showOnboarding = false)
        observeFoodsForDate()
    }

    private fun observeFoodsForDate() {
        viewModelScope.launch {
            trackerUseCases.getFoodsForDate(date = state.date).collect { trackedFood ->
                val result = trackerUseCases.calculateMealNutrients(trackedFood)
                state = state.copy(
                    caloriesGoal = result.caloriesGoal,
                    carbsGoal = result.carbsGoal,
                    proteinGoal = result.proteinGoal,
                    fatGoal = result.fatGoal,
                    totalCalories = result.totalCalories,
                    totalCarbs = result.totalCarbs,
                    totalProtein = result.totalProtein,
                    totalFat = result.totalFat,
                    meals = state.meals.map { mealState ->
                        val nutrientsForMeal = result.mealNutrients[mealState.mealType]
                        nutrientsForMeal?.let {
                            mealState.copy(
                                mealCarbs = nutrientsForMeal.carbs,
                                mealProteins = nutrientsForMeal.protein,
                                mealFats = nutrientsForMeal.fat,
                                mealCalories = nutrientsForMeal.calories,
                                isExpanded = false,
                                trackedFood = trackedFood.filter { it.mealType == mealState.mealType }
                            )
                        } ?: mealState
                    }
                )
            }
        }
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClick -> {
                UiEvent.Navigate(
                    Route.SEARCH +
                            "${event.mealType}" +
                            "${state.date.dayOfMonth}" +
                            "${state.date.month}" +
                            "${state.date.year}"
                )
            }

            is TrackerOverviewEvent.OnDeleteTrackedFood -> {
                viewModelScope.launch {
                    trackerUseCases.deleteFood(food = event.food)
                    refreshTrackerState()
                }
            }
            is TrackerOverviewEvent.OnMealExpand -> {
                state = state.copy(
                    meals = state.meals.map {
                        if(it.mealType == event.mealType){
                            it.copy(
                                isExpanded = !it.isExpanded
                            )
                        } else it
                    }
                )
            }

            TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshTrackerState()
            }
            TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(date = state.date.minusDays(1))
                refreshTrackerState()
            }
        }
    }

    private fun refreshTrackerState() {
        observeFoodsForDate()
    }
}



